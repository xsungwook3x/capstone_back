package com.develop.demo.dto;

import com.develop.demo.model.EventEntity;
import com.develop.demo.model.TownEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TownDTO {

    private Long id;
    private String town_name;
    private String town_address;
    private String town_manager_name;

    private Long userId;
    private int people_num;
    private int communication_problems;
    private int announce_num;
    private int event_num;
    private int emergency_num;
    private int urgent_announce_num;

    public TownDTO(final TownEntity entity){
        this.id=entity.getId();
        this.town_name=entity.getTown_name();
        this.town_address=entity.getTown_address();
        this.town_manager_name=entity.getTown_manager_name();
        this.userId=entity.getUserId();
        this.people_num=entity.getPeople_num();
        this.communication_problems= entity.getCommunication_problems();
        this.announce_num=entity.getAnnounce_num();
        this.event_num= entity.getEvent_num();
        this.emergency_num=entity.getEmergency_num();
        this.urgent_announce_num= entity.getUrgent_announce_num();

    }

    public static TownEntity toEntity(final TownDTO dto){
        return TownEntity.builder()
                .id(dto.getId())
                .town_name(dto.getTown_name())
                .town_address(dto.getTown_address())
                .town_manager_name(dto.getTown_manager_name())
                .userId(dto.getUserId())
                .people_num(dto.getPeople_num())
                .communication_problems(dto.getCommunication_problems())
                .announce_num(dto.getAnnounce_num())
                .event_num(dto.getEvent_num())
                .emergency_num(dto.getEmergency_num())
                .urgent_announce_num((dto.getUrgent_announce_num()))
                .build();
    }
}
