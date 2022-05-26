package com.develop.demo.controller;

import com.develop.demo.StatusEnum;
import com.develop.demo.dto.ManagerDTO;
import com.develop.demo.dto.MessageDTO;
import com.develop.demo.dto.statusDTO;
import com.develop.demo.model.ManagerEntity;
import com.develop.demo.model.MessageEntity;
import com.develop.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody MessageDTO dto){
        try{
            MessageEntity entity = MessageDTO.toEntity(dto);

            entity.setId(null);
            entity.setSuccess(true);

            LocalDateTime currentDateTime=LocalDateTime.now();
            currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            entity.setTime(currentDateTime);

            messageService.sendMessage(entity);


            List<MessageEntity> entities = messageService.retrieveByTownId(entity.getTownId());

            List<MessageDTO> dtos = entities.stream().map(MessageDTO::new).collect(Collectors.toList());

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

    @GetMapping
    public ResponseEntity<?> findAllMessage(){
        List<MessageEntity> entities = messageService.retrieveAll();

        List<MessageDTO> dtos = entities.stream().map(MessageDTO::new).collect(Collectors.toList());

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        statusDTO message = new statusDTO();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(dtos);

        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

    @GetMapping("/{town_id}")
    public ResponseEntity<?> findManager(@PathVariable Long town_id){
        List<MessageEntity> entities = messageService.retrieveByTownId(town_id);

        List<MessageDTO> dtos = entities.stream().map(MessageDTO::new).collect(Collectors.toList());

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        statusDTO message = new statusDTO();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(dtos);

        return new ResponseEntity<>(message,headers,HttpStatus.OK);


    }

    @GetMapping("/emergency/{terminal_id}")
    public ResponseEntity<?> sendEmergencyMessage(@PathVariable Long terminal_id){
        messageService.sendEmergencyMessage(terminal_id);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        statusDTO message = new statusDTO();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");

        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }


}
