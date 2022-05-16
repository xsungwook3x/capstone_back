package com.develop.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Service
public class MessagesRequestDTO{
    private String to;
    private String content;

}
