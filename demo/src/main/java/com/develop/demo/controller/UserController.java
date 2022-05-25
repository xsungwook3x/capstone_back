package com.develop.demo.controller;

import com.develop.demo.StatusEnum;
import com.develop.demo.security.TokenProvider;
import com.develop.demo.dto.UserDTO;
import com.develop.demo.dto.statusDTO;
import com.develop.demo.model.UserEntity;
import com.develop.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
        try{
            UserEntity userEntity=UserEntity.builder()
                    .userId(userDTO.getUserId())
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .username(userDTO.getUsername())
                    .role(userDTO.getRole())
                    .email(userDTO.getEmail())
                    .build();

            UserEntity registeredUser=userService.create(userEntity);
            UserDTO responseUserDTO=UserDTO.builder()
                    .id(registeredUser.getId())
                    .userId(registeredUser.getUserId())
                    .password(registeredUser.getPassword())
                    .role(registeredUser.getRole())
                    .build();

            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            statusDTO message = new statusDTO();
            message.setStatus(StatusEnum.OK);
            message.setMessage("성공 코드");
            message.setData(responseUserDTO);

            return new ResponseEntity<>(message,headers,HttpStatus.OK);

        }catch (Exception e){

            String error = e.getMessage();
            statusDTO message = new statusDTO();
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage("실패 코드");
            message.setData(error);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO){
        UserEntity user = userService.getByCredetials(userDTO.getUserId(), userDTO.getPassword());

        if(user!=null){
            final String token = tokenProvider.create(user);
            final UserDTO responseUserDTO=UserDTO.builder()
                    .userId(user.getUserId())
                    .id(user.getId())
                    .username(user.getUsername())
                    .role(user.getRole())
                    .token(token)
                    .build();

            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            statusDTO message = new statusDTO();
            message.setStatus(StatusEnum.OK);
            message.setMessage("성공 코드");
            message.setData(responseUserDTO);

            return new ResponseEntity<>(message,headers,HttpStatus.OK);
        }else{
            statusDTO message = new statusDTO();
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage("실패 코드");
            message.setData("Login failed");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}
