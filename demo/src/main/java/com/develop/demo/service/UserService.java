package com.develop.demo.service;

import com.develop.demo.model.UserEntity;
import com.develop.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity create(final UserEntity userEntity){
        if(userEntity==null||userEntity.getUserId()==null){
            throw new RuntimeException("Invalid argument");
        }
        final String userId= userEntity.getUserId();
        if(userRepository.existsByUserId(userId)){
            log.warn("Email already exists {}",userId);
            throw new RuntimeException("UserId already exists");
        }

        return userRepository.save(userEntity);
    }

    public UserEntity getByCredetials(final String userId,final String password){
        return userRepository.findByUserIdAndPassword(userId,password);
    }
}
