package com.develop.demo.service;

import com.develop.demo.model.TerminalInfoEntity;
import com.develop.demo.repository.TerminalInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TerminalInfoService {

    @Autowired
    private TerminalInfoRepository terminalInfoRepository;

    public List<TerminalInfoEntity> retreiveByTerminalId(Long terminalId){
        return terminalInfoRepository.findByTerminalId(terminalId);
    }

}
