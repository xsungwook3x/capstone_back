package com.develop.demo.dto;

import com.develop.demo.model.ManagerEntity;
import com.develop.demo.model.TownEntity;
import lombok.Data;

@Data
public class ManagerDTO {

    public Long id;
    public Long townId;
    public String name;
    public String phone;
    public String address;
    public String email;

    public ManagerDTO(final ManagerEntity entity){
        this.id=entity.getId();
        this.townId=entity.getTownId();
        this.name=entity.getName();
        this.phone=entity.getPhone();
        this.address= entity.getAddress();
        this.email= entity.getEmail();
    }

    public static ManagerEntity toEntity(final ManagerDTO dto){
        return ManagerEntity.builder()
                .id(dto.getId())
                .townId(dto.getTownId())
                .address(dto.getAddress())
                .name(dto.getName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
    }

}
