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
@Table(name="managers")
public class ManagerEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="manager_id")
    private Long id;

    @Column(name="town_id")
    private Long townId;

    private String name;
    private String phone;
    private String address;
    private String email;
}
