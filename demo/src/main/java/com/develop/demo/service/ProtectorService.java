package com.develop.demo.service;

import com.develop.demo.model.ManagerEntity;
import com.develop.demo.model.ProtectorEntity;
import com.develop.demo.repository.ProtectorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProtectorService {

    @Autowired
    private ProtectorRepository protectorRepository;

    public List<ProtectorEntity> retrieveAll(){
        return protectorRepository.findAll();
    }

    public List<ProtectorEntity> retrieveByTownId(Long townId){
        return protectorRepository.findByTownId(townId);
    }

    public List<ProtectorEntity> retrieveByTerminalId(Long terminalId){
        return protectorRepository.findByTerminalId(terminalId);
    }

    public Optional<ProtectorEntity> retrieveById(Long id){
        return protectorRepository.findById(id);
    }

    public void create(ProtectorEntity entity){
        protectorRepository.save(entity);
    }

    public Optional<ProtectorEntity> update(ProtectorEntity entity){
        final Optional<ProtectorEntity> original= protectorRepository.findById(entity.getId());

        original.ifPresent(protector -> {
            protector.setTownId(entity.getTownId());

            protector.setTerminalId(entity.getTerminalId());

            protector.setName(entity.getName());

            protector.setPhone(entity.getPhone());

            protectorRepository.save(protector);
        });

        return retrieveById(entity.getId());
    }

    public void delete(Long id){
        protectorRepository.deleteById(id);
    }
}
