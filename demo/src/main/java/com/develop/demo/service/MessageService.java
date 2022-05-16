package com.develop.demo.service;

import com.develop.demo.model.MessageEntity;
import com.develop.demo.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SmsService smsService;

    public void sendMessage(MessageEntity entity){
        int statusCode=smsService.sendSMS(entity.getTownId(), entity.getTarget(),entity.getContent());
        if(statusCode==202){
            entity.setSuccess(true);
        }
        else{
            entity.setSuccess(false);
        }
        messageRepository.save(entity);
    }

    public List<MessageEntity> retrieveAll(){
        return messageRepository.findAll();
    }

    public List<MessageEntity> retrieveByTownId(Long townId){
        return messageRepository.findByTownId(townId);
    }

    public void remove(Long id){
        messageRepository.deleteById(id);
    }
}
