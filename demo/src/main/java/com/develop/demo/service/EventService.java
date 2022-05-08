package com.develop.demo.service;

import com.develop.demo.model.EventEntity;
import com.develop.demo.model.ManagerEntity;
import com.develop.demo.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public List<EventEntity> retrieveAll(){
        return eventRepository.findAll();
    }

    public Optional<EventEntity> retrieveById(Long id){
        return eventRepository.findById(id);
    }

    public List<EventEntity> retrieveByTown(Long townId){

        return eventRepository.findByTownId(townId);
    }

    public void create(EventEntity entity){
        eventRepository.save(entity);
    }

    public Optional<EventEntity> update(EventEntity entity){
        final Optional<EventEntity> original= eventRepository.findById(entity.getId());

        original.ifPresent(event -> {
            event.setTownId(entity.getTownId());
            event.setTitle(entity.getTitle());
            event.setContent(entity.getContent());
            event.setFromEventDate(entity.getFromEventDate());
            event.setToEventDate(entity.getToEventDate());
            event.setCreatedDatetime(entity.getCreatedDatetime());

            eventRepository.save(event);
        });

        return retrieveById(entity.getId());
    }

    public void delete(Long id){
        eventRepository.deleteById(id);
    }
}
