package com.develop.demo.service;

import com.develop.demo.model.ManagerEntity;
import com.develop.demo.model.ProtectorEntity;
import com.develop.demo.repository.ManagerRepository;
import com.develop.demo.repository.MessageRepository;
import com.develop.demo.repository.ProtectorRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;



import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Component
public class SmsService {

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private ProtectorRepository protectorRepository;

    private String makeSignature(String url, String timestamp, String method, String accessKey, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        String space = " ";
        String newLine = "\n";
        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();




        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }

    private ArrayList<JSONObject> makeEmergencyMessage(Long terminalId){
        ArrayList<JSONObject> toArr = new ArrayList<JSONObject>();
        List<ProtectorEntity> protectors = protectorRepository.findByTerminalId(terminalId);
        for(ProtectorEntity m:protectors){
            String phone=m.getPhone();
            JSONObject toOb= new JSONObject();
            toOb.put("to",phone);
            toArr.add(toOb);
        }
        return toArr;
    }
    private ArrayList<JSONObject> makeMessage(String target,Long townId){
        ArrayList<JSONObject> toArr = new ArrayList<JSONObject>();
        if(target.equals("m")){
            List<ManagerEntity> managers=managerRepository.findByTownId(townId);
            for(ManagerEntity m:managers){
                String phone=m.getPhone();
                JSONObject toOb= new JSONObject();
                toOb.put("to",phone);
                toArr.add(toOb);
            }
        } else if (target.equals("p")) {
            List<ProtectorEntity> protectors = protectorRepository.findByTownId(townId);
            for(ProtectorEntity m:protectors){
                String phone=m.getPhone();
                JSONObject toOb= new JSONObject();
                toOb.put("to",phone);
                toArr.add(toOb);
            }
        }
        else{
            List<ManagerEntity> managers=managerRepository.findByTownId(townId);
            for(ManagerEntity m:managers){
                String phone=m.getPhone();
                JSONObject toOb= new JSONObject();
                toOb.put("to",phone);
                toArr.add(toOb);
            }

            List<ProtectorEntity> protectors = protectorRepository.findByTownId(townId);
            for(ProtectorEntity m:protectors){
                String phone=m.getPhone();
                JSONObject toOb= new JSONObject();
                toOb.put("to",phone);
                toArr.add(toOb);
            }
        }


        return toArr;
    }

    public int sendSMS(Long townId,String target,String content) {
        String hostNameUrl = "https://sens.apigw.ntruss.com"; // ????????? URL
        String requestUrl= "/sms/v2/services/"; // ?????? URL
        String requestUrlType = "/messages"; // ?????? URL
        String accessKey = "sdduCGbwIvRifvA2V9WN"; // ????????? ???????????? ????????? ???????????? ???????????? ?????? ????????? // Access Key : https://www.ncloud.com/mypage/manage/info > ????????? ?????? > Access Key ID
        String secretKey = "ZSeteLAkpn6Tg9FoojAW1R2qdvM1t9W0islbGBvx"; // 2??? ????????? ?????? ??????????????? ???????????? service secret key // Service Key : https://www.ncloud.com/mypage/manage/info > ????????? ?????? > Access Key ID
        String serviceId = "ncp:sms:kr:274183071686:smartsystem"; // ??????????????? ????????? SMS ????????? ID // service ID : https://console.ncloud.com/sens/project > Simple & ... > Project > ????????? ID
        String method = "POST"; // ?????? method
        String timestamp = Long.toString(System.currentTimeMillis()); // current timestamp (epoch)
        requestUrl += serviceId + requestUrlType;
        String apiUrl = hostNameUrl + requestUrl; // JSON ??? ????????? body data ??????

        JSONObject bodyJson = new JSONObject();

        JSONObject toJson2 = new JSONObject();
        ArrayList<JSONObject> toArr = makeMessage(target,townId);

        bodyJson.put("type","SMS"); // Madantory, ????????? Type (SMS | LMS | MMS), (????????? ??????) //

        bodyJson.put("from","01050004942"); // Mandatory, ????????????, ?????? ????????? ??????????????? ?????? ??????
        bodyJson.put("content",content); // Mandatory(??????), ?????? ????????? ??????, SMS: ?????? 80byte, LMS, MMS: ?????? 2000byte
        bodyJson.put("messages", toArr); // Mandatory(??????), ?????? ????????? ?????? (messages.XXX), ?????? 1,000??? //String body = bodyJson.toJSONString();
        String body = bodyJson.toString();
        System.out.println(body);
        System.out.println(toArr);
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("content-type", "application/json");
            con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
            con.setRequestProperty("x-ncp-iam-access-key", accessKey);
            con.setRequestProperty("x-ncp-apigw-signature-v2", makeSignature(requestUrl, timestamp, method, accessKey, secretKey));
            con.setRequestMethod(method); con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(body.getBytes()); wr.flush(); wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br; System.out.println("responseCode" +" " + responseCode);
            if(responseCode == 202) { // ?????? ??????
                br = new BufferedReader(new InputStreamReader(con.getInputStream())); }
            else { // ?????? ??????
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
            return responseCode;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int sendEmergencySMS(Long terminalId) {
        String hostNameUrl = "https://sens.apigw.ntruss.com"; // ????????? URL
        String requestUrl= "/sms/v2/services/"; // ?????? URL
        String requestUrlType = "/messages"; // ?????? URL
        String accessKey = "sdduCGbwIvRifvA2V9WN"; // ????????? ???????????? ????????? ???????????? ???????????? ?????? ????????? // Access Key : https://www.ncloud.com/mypage/manage/info > ????????? ?????? > Access Key ID
        String secretKey = "ZSeteLAkpn6Tg9FoojAW1R2qdvM1t9W0islbGBvx"; // 2??? ????????? ?????? ??????????????? ???????????? service secret key // Service Key : https://www.ncloud.com/mypage/manage/info > ????????? ?????? > Access Key ID
        String serviceId = "ncp:sms:kr:274183071686:smartsystem"; // ??????????????? ????????? SMS ????????? ID // service ID : https://console.ncloud.com/sens/project > Simple & ... > Project > ????????? ID
        String method = "POST"; // ?????? method
        String timestamp = Long.toString(System.currentTimeMillis()); // current timestamp (epoch)
        requestUrl += serviceId + requestUrlType;
        String apiUrl = hostNameUrl + requestUrl; // JSON ??? ????????? body data ??????

        JSONObject bodyJson = new JSONObject();

        JSONObject toJson2 = new JSONObject();
        ArrayList<JSONObject> toArr = makeEmergencyMessage(terminalId);

        bodyJson.put("type","SMS"); // Madantory, ????????? Type (SMS | LMS | MMS), (????????? ??????) //

        bodyJson.put("from","01050004942"); // Mandatory, ????????????, ?????? ????????? ??????????????? ?????? ??????
        bodyJson.put("content","[??????] ?????????????????? ?????? ???????????? ??????????????????."); // Mandatory(??????), ?????? ????????? ??????, SMS: ?????? 80byte, LMS, MMS: ?????? 2000byte
        bodyJson.put("messages", toArr); // Mandatory(??????), ?????? ????????? ?????? (messages.XXX), ?????? 1,000??? //String body = bodyJson.toJSONString();
        String body = bodyJson.toString();
        System.out.println(body);
        System.out.println(toArr);
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("content-type", "application/json");
            con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
            con.setRequestProperty("x-ncp-iam-access-key", accessKey);
            con.setRequestProperty("x-ncp-apigw-signature-v2", makeSignature(requestUrl, timestamp, method, accessKey, secretKey));
            con.setRequestMethod(method); con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(body.getBytes()); wr.flush(); wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br; System.out.println("responseCode" +" " + responseCode);
            if(responseCode == 202) { // ?????? ??????
                br = new BufferedReader(new InputStreamReader(con.getInputStream())); }
            else { // ?????? ??????
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
            return responseCode;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

}
