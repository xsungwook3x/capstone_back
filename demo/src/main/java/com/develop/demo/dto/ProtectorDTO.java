package com.develop.demo.dto;

import com.develop.demo.model.ManagerEntity;
import com.develop.demo.model.ProtectorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProtectorDTO {

    private Long id;
    private Long townId;
    private Long terminalId;
    private String name;
    private String phone;


    public ProtectorDTO(final ProtectorEntity entity){
        this.id=entity.getId();
        this.townId=entity.getTownId();
        this.terminalId=entity.getTerminalId();
        this.name=entity.getName();
        this.phone=entity.getPhone();

    }

    public static ProtectorEntity toEntity(final ProtectorDTO dto){
        return ProtectorEntity.builder()
                .id(dto.getId())
                .townId(dto.getTownId())
                .terminalId(dto.getTerminalId())
                .name(dto.getName())
                .phone(dto.getPhone())
                .build();
    }
}
