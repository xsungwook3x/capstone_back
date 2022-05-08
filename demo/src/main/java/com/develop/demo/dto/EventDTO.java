package com.develop.demo.dto;

import com.develop.demo.model.EventEntity;
import com.develop.demo.model.ManagerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventDTO {

    private Long id;

    private Long townId;

    private String title;

    private String content;

    private LocalDateTime createdDatetime;

    private LocalDate fromEventDate;

    private LocalDate toEventDate;

    public EventDTO(final EventEntity entity){
        this.id=entity.getId();
        this.townId=entity.getTownId();
        this.title=entity.getTitle();
        this.content=entity.getContent();
        this.createdDatetime= entity.getCreatedDatetime();
        this.fromEventDate= entity.getFromEventDate();
        this.toEventDate=entity.getToEventDate();
    }

    public static EventEntity toEntity(final EventDTO dto){
        return EventEntity.builder()
                .id(dto.getId())
                .townId(dto.getTownId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .createdDatetime(dto.getCreatedDatetime())
                .fromEventDate(dto.getFromEventDate())
                .toEventDate(dto.getToEventDate())
                .build();
    }
}
