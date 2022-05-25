package com.develop.demo.controller;

import com.develop.demo.StatusEnum;
import com.develop.demo.dto.EventDTO;
import com.develop.demo.dto.statusDTO;
import com.develop.demo.model.EventEntity;
import com.develop.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    EventService eventService;

    public LocalDate formatDateTime(LocalDate date){
        return LocalDate.parse(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @PostMapping("/")
    public ResponseEntity<?> createEvent(@RequestBody EventDTO dto){
        try{
            EventEntity entity = EventDTO.toEntity(dto);

            entity.setId(null);

            LocalDateTime currentDateTime=LocalDateTime.now();
            currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            entity.setCreatedDatetime(currentDateTime);

            entity.setFromEventDate(formatDateTime(entity.getFromEventDate()));
            entity.setToEventDate(formatDateTime(entity.getToEventDate()));

            eventService.create(entity);

            List<EventEntity> entities = eventService.retrieveByTown(entity.getTownId());

            List<EventDTO> dtos = entities.stream().map(EventDTO::new).collect(Collectors.toList());

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
    public ResponseEntity<?> findAllEvents(){
        try {
            List<EventEntity> entities = eventService.retrieveAll();

            List<EventDTO> dtos = entities.stream().map(EventDTO::new).collect(Collectors.toList());

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
    public ResponseEntity<?> findEvent(@PathVariable Long town_id){
        try {
            List<EventEntity> entities = eventService.retrieveByTown(town_id);

            List<EventDTO> dtos = entities.stream().map(EventDTO::new).collect(Collectors.toList());

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

    @PostMapping("/{event_id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long event_id, @RequestBody EventDTO dto){

        try {
            EventEntity entity = EventDTO.toEntity(dto);

            entity.setId(event_id);
            entity.setFromEventDate(formatDateTime(entity.getFromEventDate()));
            entity.setToEventDate(formatDateTime(entity.getToEventDate()));

            LocalDateTime currentDateTime=LocalDateTime.now();
            currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            entity.setCreatedDatetime(currentDateTime);

            Optional<EventEntity> entities = eventService.update(entity);
            List<EventDTO> dtos = entities.stream().map(EventDTO::new).collect(Collectors.toList());

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

    @DeleteMapping("/{event_id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long event_id){

        try {
            eventService.delete(event_id);

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
