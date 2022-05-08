package com.develop.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="announces")
public class AnnounceEntity {

    @Id
    @GeneratedValue
    @Column(name="announce_id")
    private Long id;

    @Column(name="town_id")
    private Long townId;

    private String title;

    private String content;

    private LocalDateTime createdDatetime;
}
