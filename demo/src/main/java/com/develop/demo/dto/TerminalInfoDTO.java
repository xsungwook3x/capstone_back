package com.develop.demo.dto;

import com.develop.demo.model.TerminalEntity;
import com.develop.demo.model.TerminalInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TerminalInfoDTO {

    private Long id;

    private Long terminalId;

    private LocalDateTime time;

    private int temper_humid_sensor;

    private int smoke_sensor;

    private int camera_sensor;

    private int motion_sensor;

    private int illuminance_sensor;

    private int flame_sensor;

    public TerminalInfoDTO(final TerminalInfoEntity entity){
        this.id=entity.getId();
        this.terminalId=entity.getTerminalId();
        this.time=entity.getTime();
        this.temper_humid_sensor=entity.getTemper_humid_sensor();
        this.smoke_sensor= entity.getSmoke_sensor();
        this.camera_sensor=entity.getCamera_sensor();
        this.motion_sensor=entity.getMotion_sensor();
        this.illuminance_sensor=entity.getIlluminance_sensor();
        this.flame_sensor=entity.getFlame_sensor();
    }

    public static TerminalInfoEntity toEntity(final TerminalInfoDTO dto){
        return TerminalInfoEntity.builder()
                .id(dto.getId())
                .terminalId(dto.getTerminalId())
                .time(dto.getTime())
                .temper_humid_sensor(dto.getTemper_humid_sensor())
                .smoke_sensor(dto.getSmoke_sensor())
                .camera_sensor(dto.getCamera_sensor())
                .motion_sensor(dto.getMotion_sensor())
                .illuminance_sensor(dto.getIlluminance_sensor())
                .flame_sensor(dto.getFlame_sensor())
                .build();
    }
}
