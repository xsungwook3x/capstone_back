package com.develop.demo.controller;

import com.develop.demo.StatusEnum;
import com.develop.demo.dto.ProtectorDTO;
import com.develop.demo.dto.TerminalInfoDTO;
import com.develop.demo.dto.statusDTO;
import com.develop.demo.model.ProtectorEntity;
import com.develop.demo.model.TerminalInfoEntity;
import com.develop.demo.service.TerminalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/terminal_info")
public class TerminalInfoController {

    @Autowired
    private TerminalInfoService terminalInfoService;

    @GetMapping("/{terminal_id}")
    public ResponseEntity<?> findByTerminalId(@PathVariable Long terminal_id){
        try{
            List<TerminalInfoEntity> entities = terminalInfoService.retreiveByTerminalId(terminal_id);
            List<TerminalInfoDTO> dtos = entities.stream().map(TerminalInfoDTO::new).collect(Collectors.toList());

            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            statusDTO message = new statusDTO();
            message.setStatus(StatusEnum.OK);
            message.setMessage("성공 코드");
            message.setData(dtos);

            return new ResponseEntity<>(message,headers, HttpStatus.OK);
        }catch (Exception e){
            String error = e.getMessage();
            statusDTO message = new statusDTO();
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage("실패 코드");
            message.setData(error);
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
    }
}
