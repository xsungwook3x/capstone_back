package com.develop.demo.service;

import com.develop.demo.model.AnnounceEntity;
import com.develop.demo.repository.AnnounceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AnnounceService {

    @Autowired
    private AnnounceRepository announceRepository;


    public List<AnnounceEntity> findAll(){return announceRepository.findAll();}

    public List<AnnounceEntity> findByTownId(Long townId){return announceRepository.findByTownId(townId);}

    public Optional<AnnounceEntity> findById(Long id){return announceRepository.findById(id);}

    public void delete(Long id){ announceRepository.deleteById(id);}
}
