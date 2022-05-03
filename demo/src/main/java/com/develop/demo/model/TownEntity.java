package com.develop.demo.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class TownEntity {

    @Id @GeneratedValue
    @Column(name ="town_id")
    private Long id;
    private String town_name;
    private String town_address;
    private String town_manager_name;
    private String town_manager_id;
    private int people_num;
    private int communication_problems;
    private int announce_num;
    private int event_num;
    private int emergency_num;
    private int urgent_announce_num;
}
