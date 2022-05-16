package com.develop.demo.dto;

import com.develop.demo.model.ManagerEntity;
import com.develop.demo.model.MessageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageDTO {

    public Long id;
    public Long townId;
    public String target;
    public String content;
    public boolean success;
    public LocalDateTime time;


    public MessageDTO(final MessageEntity entity){
        this.id=entity.getId();
        this.townId=entity.getTownId();
        this.target=entity.getTarget();
        this.content=entity.getContent();
        this.success= entity.isSuccess();
        this.time= entity.getTime();
    }

    public static MessageEntity toEntity(final MessageDTO dto){
        return MessageEntity.builder()
                .id(dto.getId())
                .townId(dto.getTownId())
                .target(dto.getTarget())
                .content(dto.getContent())
                .success(dto.isSuccess())
                .time(dto.getTime())
                .build();
    }
}
