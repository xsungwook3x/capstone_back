package com.develop.demo.controller;

import com.develop.demo.StatusEnum;
import com.develop.demo.dto.TownDTO;
import com.develop.demo.dto.statusDTO;
import com.develop.demo.model.TownEntity;
import com.develop.demo.service.TownService;
import org.hibernate.engine.internal.ImmutableEntityEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/town")
public class TownController {

    @Autowired
    private TownService service;

    @PostMapping
    public ResponseEntity<?> createTown(@RequestBody TownDTO dto){
        try{
            Long temporaryId = Long.valueOf(111111111);

            TownEntity entity = TownDTO.toEntity(dto);

            //자동생성이기때문에 초기화
            entity.setId(null);

            entity.setUserId(temporaryId);

            service.create(entity);

            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            statusDTO message = new statusDTO();
            message.setStatus(StatusEnum.OK);
            message.setMessage("성공 코드");

            return new ResponseEntity<>(message,headers,HttpStatus.OK);
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
    public ResponseEntity<?> findAllTown(){
        List<TownEntity> entities = service.retreiveAll();

        List<TownDTO> dtos = entities.stream().map(TownDTO::new).collect(Collectors.toList());

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        statusDTO message = new statusDTO();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(dtos);

        return new ResponseEntity<>(message,headers,HttpStatus.OK);

    }
    @GetMapping("/{user_id}")
    public ResponseEntity<?> findTown(@PathVariable Long user_id){
        List<TownEntity> entities = service.retreiveTown(user_id);

        List<TownDTO> dtos = entities.stream().map(TownDTO::new).collect(Collectors.toList());

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        statusDTO message = new statusDTO();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(dtos);

        return new ResponseEntity<>(message,headers,HttpStatus.OK);

    }



    @PostMapping("/{town_id}")
    public ResponseEntity<?> updateTown(@PathVariable Long town_id,@RequestBody TownDTO dto){

        Long temporaryId = Long.valueOf(111111111);

        TownEntity entity = TownDTO.toEntity(dto);

        entity.setId(town_id);
        entity.setUserId(temporaryId);

        List<TownEntity> entities=service.update(entity);
        List<TownDTO> dtos = entities.stream().map(TownDTO::new).collect(Collectors.toList());

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        statusDTO message = new statusDTO();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(dtos);

        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

    @DeleteMapping("/{town_id}")
    public ResponseEntity<?> deleteTown(@PathVariable Long town_id){

        try {
            service.delete(town_id);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            statusDTO message = new statusDTO();
            message.setStatus(StatusEnum.OK);
            message.setMessage("성공 코드");

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
