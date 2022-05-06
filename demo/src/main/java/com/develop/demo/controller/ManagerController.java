package com.develop.demo.controller;

import com.develop.demo.StatusEnum;
import com.develop.demo.dto.ManagerDTO;
import com.develop.demo.dto.TownDTO;
import com.develop.demo.dto.statusDTO;
import com.develop.demo.model.ManagerEntity;
import com.develop.demo.model.TownEntity;
import com.develop.demo.service.ManagerService;
import com.develop.demo.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping("/")
    public ResponseEntity<?> createManager(@RequestBody ManagerDTO dto){
        try{
            ManagerEntity entity = ManagerDTO.toEntity(dto);

            entity.setId(null);

            managerService.create(entity);

            List<ManagerEntity> entities = managerService.retrieveByTown(entity.getTownId());

            List<ManagerDTO> dtos = entities.stream().map(ManagerDTO::new).collect(Collectors.toList());

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

    @GetMapping("/")
    public ResponseEntity<?> findAllManager(){
        List<ManagerEntity> entities = managerService.retrieveAll();

        List<ManagerDTO> dtos = entities.stream().map(ManagerDTO::new).collect(Collectors.toList());

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
        List<ManagerEntity> entities = managerService.retrieveByTown(town_id);

        List<ManagerDTO> dtos = entities.stream().map(ManagerDTO::new).collect(Collectors.toList());

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        statusDTO message = new statusDTO();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(dtos);

        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

    @PostMapping("/{manager_id}")
    public ResponseEntity<?> updateManager(@PathVariable Long manager_id, @RequestBody ManagerDTO dto){

        ManagerEntity entity = ManagerDTO.toEntity(dto);

        entity.setId(manager_id);

        Optional<ManagerEntity> entities=managerService.update(entity);
        List<ManagerDTO> dtos = entities.stream().map(ManagerDTO::new).collect(Collectors.toList());

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        statusDTO message = new statusDTO();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(dtos);

        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

    @DeleteMapping("/{manager_id}")
    public ResponseEntity<?> deleteManager(@PathVariable Long manager_id){

        try {
            managerService.delete(manager_id);

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
