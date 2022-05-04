package com.develop.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="towns")
public class TownEntity {

    @Id @GeneratedValue
    @Column(name ="town_id")
    private Long id;

    @Column(name="user_id")
    private Long userId;

    private String town_name;
    private String town_address;
    private String town_manager_name;

    private int people_num;
    private int communication_problems;
    private int announce_num;
    private int event_num;
    private int emergency_num;
    private int urgent_announce_num;
}
