package com.develop.demo.controller;

import com.develop.demo.StatusEnum;
import com.develop.demo.dto.AnnounceDTO;
import com.develop.demo.dto.EventDTO;
import com.develop.demo.dto.statusDTO;
import com.develop.demo.model.AnnounceEntity;
import com.develop.demo.model.EventEntity;
import com.develop.demo.service.AnnounceService;
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
@RequestMapping("/api/announce")
public class AnnounceController {

    @Autowired
    private AnnounceService announceService;

    @PostMapping("/")
    public ResponseEntity<?> createAnnouncement(@RequestBody AnnounceDTO dto){
        try{
            AnnounceEntity entity = AnnounceDTO.toEntity(dto);

            entity.setId(null);

            LocalDateTime currentDateTime=LocalDateTime.now();
            currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            entity.setCreatedDatetime(currentDateTime);


            announceService.create(entity);


            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            statusDTO message = new statusDTO();
            message.setStatus(StatusEnum.OK);
            message.setMessage("성공 코드");

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

    @GetMapping("/")
    public ResponseEntity<?> findAllAnnouncement(){
        try {
            List<AnnounceEntity> entities = announceService.findAll();

            List<AnnounceDTO> dtos = entities.stream().map(AnnounceDTO::new).collect(Collectors.toList());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            statusDTO message = new statusDTO();
            message.setStatus(StatusEnum.OK);
            message.setMessage("성공 코드");
            message.setData(dtos);

            return new ResponseEntity<>(message, headers, HttpStatus.OK);
        }catch (Exception e){
            String error = e.getMessage();
            statusDTO message = new statusDTO();
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage("실패 코드");
            message.setData(error);
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{town_id}")
    public ResponseEntity<?> findAnnoucementByTownId(@PathVariable Long town_id){
        try {
            List<AnnounceEntity> entities = announceService.findByTownId(town_id);

            List<AnnounceDTO> dtos = entities.stream().map(AnnounceDTO::new).collect(Collectors.toList());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            statusDTO message = new statusDTO();
            message.setStatus(StatusEnum.OK);
            message.setMessage("성공 코드");
            message.setData(dtos);

            return new ResponseEntity<>(message, headers, HttpStatus.OK);
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
