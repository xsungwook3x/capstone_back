package com.develop.demo.service;

import com.develop.demo.model.ManagerEntity;
import com.develop.demo.model.TownEntity;
import com.develop.demo.repository.ManagerRepository;
import com.develop.demo.repository.TownRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ManagerService {

    @Autowired
    private ManagerRepository repository;



    public List<ManagerEntity> retrieveAll(){
        return repository.findAll();
    }

    public Optional<ManagerEntity> retrieveById(Long id){
        return repository.findById(id);
    }

    public List<ManagerEntity> retrieveByTown(Long townId){

        return repository.findByTownId(townId);
    }

    public void create(ManagerEntity entity){
        repository.save(entity);
    }

    public Optional<ManagerEntity> update(ManagerEntity entity){
        final Optional<ManagerEntity> original= repository.findById(entity.getId());

        original.ifPresent(manager -> {
            manager.setTownId(entity.getTownId());
            manager.setName(entity.getName());
            manager.setAddress(entity.getAddress());
            manager.setEmail(entity.getEmail());
            manager.setPhone(entity.getPhone());

            repository.save(manager);
        });

        return retrieveById(entity.getId());
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
