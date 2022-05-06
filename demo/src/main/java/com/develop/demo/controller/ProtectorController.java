package com.develop.demo.controller;

import com.develop.demo.StatusEnum;
import com.develop.demo.dto.ProtectorDTO;
import com.develop.demo.dto.TerminalDTO;
import com.develop.demo.dto.statusDTO;
import com.develop.demo.model.ProtectorEntity;
import com.develop.demo.model.TerminalEntity;
import com.develop.demo.service.ProtectorService;
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
@RequestMapping("/api/protector")
public class ProtectorController {

    @Autowired
    private ProtectorService protectorService;


    @PostMapping
    public ResponseEntity<?> createProtector(@RequestBody ProtectorDTO dto){

        try {
            ProtectorEntity entity = ProtectorDTO.toEntity(dto);

            entity.setId(null);

            protectorService.create(entity);

            List<ProtectorEntity> entities = protectorService.retrieveByTownId(entity.getTownId());
            List<ProtectorDTO> dtos = entities.stream().map(ProtectorDTO::new).collect(Collectors.toList());

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
    public ResponseEntity<?> findAllProtectors(){

        List<ProtectorEntity> entities = protectorService.retrieveAll();
        List<ProtectorDTO> dtos = entities.stream().map(ProtectorDTO::new).collect(Collectors.toList());

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        statusDTO message = new statusDTO();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(dtos);

        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }

    @GetMapping("/{town_id}")
    public ResponseEntity<?> findProtectorByTownId(@PathVariable Long town_id){
        try{
            List<ProtectorEntity> entities = protectorService.retrieveByTownId(town_id);
            List<ProtectorDTO> dtos = entities.stream().map(ProtectorDTO::new).collect(Collectors.toList());

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

    @GetMapping("/terminal/{terminal_id}")
    public ResponseEntity<?> findProtectorByTerminalId(@PathVariable Long terminal_id){
        try{
            List<ProtectorEntity> entities = protectorService.retrieveByTerminalId(terminal_id);
            List<ProtectorDTO> dtos = entities.stream().map(ProtectorDTO::new).collect(Collectors.toList());

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

    @PostMapping("/{protector_id}")
    public ResponseEntity<?> updateTerminal(@PathVariable Long protector_id, @RequestBody ProtectorDTO dto){
        try{
            ProtectorEntity entity = ProtectorDTO.toEntity(dto);

            entity.setId(protector_id);

            Optional<ProtectorEntity> entities=protectorService.update(entity);
            List<ProtectorDTO> dtos = entities.stream().map(ProtectorDTO::new).collect(Collectors.toList());

            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            statusDTO message = new statusDTO();
            message.setStatus(StatusEnum.OK);
            message.setMessage("성공 코드");
            message.setData(dtos);

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

    @DeleteMapping("/{protector_id}")
    public ResponseEntity<?> deleteManager(@PathVariable Long protector_id){

        try {
            protectorService.delete(protector_id);

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
