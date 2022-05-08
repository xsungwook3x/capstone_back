package com.develop.demo.dto;

import com.develop.demo.model.AnnounceEntity;
import com.develop.demo.model.EventEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnnounceDTO {

    private Long id;
    private Long townId;
    private String title;
    private String content;
    private LocalDateTime createdDatetime;

    public AnnounceDTO(final AnnounceEntity entity){
        this.id=entity.getId();
        this.townId=entity.getTownId();
        this.title=entity.getTitle();
        this.content=entity.getContent();
        this.createdDatetime= entity.getCreatedDatetime();

    }

    public static AnnounceEntity toEntity(final AnnounceDTO dto){
        return AnnounceEntity.builder()
                .id(dto.getId())
                .townId(dto.getTownId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .createdDatetime(dto.getCreatedDatetime())
                .build();
    }
}
