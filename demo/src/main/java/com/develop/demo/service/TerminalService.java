package com.develop.demo.service;

import com.develop.demo.model.ManagerEntity;
import com.develop.demo.model.TerminalEntity;
import com.develop.demo.repository.TerminalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TerminalService {

    @Autowired
    TerminalRepository repository;

    public List<TerminalEntity> retrieveAll(){
        return repository.findAll();
    }

    public Optional<TerminalEntity> retrieveById(Long id){
        return repository.findById(id);
    }

    public List<TerminalEntity> retrieveByTownId(Long townId){
        return repository.findByTownId(townId);
    }

    public void create(TerminalEntity entity){
        repository.save(entity);
    }

    public Optional<TerminalEntity> update(TerminalEntity entity){
        final Optional<TerminalEntity> original= repository.findById(entity.getId());

        original.ifPresent(terminal -> {
            terminal.setTownId(entity.getTownId());
            terminal.setName(entity.getName());
            terminal.setAddress(entity.getAddress());
            terminal.setPhone(entity.getPhone());

            repository.save(terminal);
        });

        return retrieveById(entity.getId());
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
