package com.develop.demo.dto;

import com.develop.demo.model.ManagerEntity;
import com.develop.demo.model.TerminalEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TerminalDTO {

    private Long id;

    private Long townId;

    private String name;
    private String phone;
    private String address;

    public TerminalDTO(final TerminalEntity entity){
        this.id=entity.getId();
        this.townId=entity.getTownId();
        this.name=entity.getName();
        this.phone=entity.getPhone();
        this.address= entity.getAddress();
    }

    public static TerminalEntity toEntity(final TerminalDTO dto){
        return TerminalEntity.builder()
                .id(dto.getId())
                .townId(dto.getTownId())
                .address(dto.getAddress())
                .name(dto.getName())
                .phone(dto.getPhone())
                .build();
    }
}
