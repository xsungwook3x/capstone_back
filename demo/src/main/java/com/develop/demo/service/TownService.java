package com.develop.demo.service;

import com.develop.demo.model.EventEntity;
import com.develop.demo.model.TownEntity;
import com.develop.demo.repository.TownRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TownService {

    @Autowired
    private TownRepository townRepository;

    public List<TownEntity> retreiveAll(){
        return townRepository.findAll();
    }

    public Optional<TownEntity> retrieveTownById(Long id){return townRepository.findById(id);}

    public List<TownEntity> retreiveTown(final Long userId){
        return townRepository.findByUserId(userId);
    }

    public void create(final TownEntity entity){
        townRepository.save(entity);
    }

    public List<TownEntity> update(final TownEntity entity){

        final Optional<TownEntity> original= townRepository.findById(entity.getId());

        original.ifPresent(town -> {
            town.setTown_address(entity.getTown_address());
            town.setTown_name(entity.getTown_name());
            town.setTown_manager_name(entity.getTown_manager_name());
            town.setUserId(entity.getUserId());
            town.setPeople_num(entity.getPeople_num());
            town.setAnnounce_num(entity.getAnnounce_num());
            town.setCommunication_problems(entity.getCommunication_problems());
            town.setEmergency_num(entity.getEmergency_num());
            town.setUrgent_announce_num(entity.getUrgent_announce_num());


            townRepository.save(town);
        });

        return retreiveTown(entity.getUserId());
    }






    public void delete(final Long id){
        try{
            townRepository.deleteById(id);
        }catch (Exception e){
            log.error("error deleting town entity",id,e);

            throw new RuntimeException("error deleting entity"+id);
        }

    }
}
