package com.develop.demo.controller;

import com.develop.demo.StatusEnum;
import com.develop.demo.dto.ManagerDTO;
import com.develop.demo.dto.TerminalDTO;
import com.develop.demo.dto.statusDTO;
import com.develop.demo.model.ManagerEntity;
import com.develop.demo.model.TerminalEntity;
import com.develop.demo.service.TerminalService;
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
@RequestMapping("/api/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @PostMapping
    public ResponseEntity<?> createTerminal(@RequestBody TerminalDTO dto){

        try {
            TerminalEntity entity = TerminalDTO.toEntity(dto);

            entity.setId(null);

            terminalService.create(entity);

            List<TerminalEntity> entities = terminalService.retrieveByTownId(entity.getTownId());
            List<TerminalDTO> dtos = entities.stream().map(TerminalDTO::new).collect(Collectors.toList());

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
    public ResponseEntity<?> findAllTerminal(){

        List<TerminalEntity> entities = terminalService.retrieveAll();
        List<TerminalDTO> dtos = entities.stream().map(TerminalDTO::new).collect(Collectors.toList());

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        statusDTO message = new statusDTO();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(dtos);

        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }

    @GetMapping("/{town_id}")
    public ResponseEntity<?> findByTownId(@PathVariable Long town_id){
        try{
            List<TerminalEntity> entities = terminalService.retrieveByTownId(town_id);
            List<TerminalDTO> dtos = entities.stream().map(TerminalDTO::new).collect(Collectors.toList());

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

    @PostMapping("/{terminal_id}")
    public ResponseEntity<?> updateTerminal(@PathVariable Long terminal_id, @RequestBody TerminalDTO dto){
        try{
            TerminalEntity entity = TerminalDTO.toEntity(dto);

            entity.setId(terminal_id);

            Optional<TerminalEntity> entities=terminalService.update(entity);
            List<TerminalDTO> dtos = entities.stream().map(TerminalDTO::new).collect(Collectors.toList());

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

    @DeleteMapping("/{terminal_id}")
    public ResponseEntity<?> deleteManager(@PathVariable Long terminal_id){

        try {
            terminalService.delete(terminal_id);

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
