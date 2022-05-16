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
@Table(name="messages")
public class MessageEntity {

    @Id @GeneratedValue
    @Column(name="message_id")
    private Long id;

    @Column(name="town_id")
    private Long townId;

    private String target;
    private String content;
    private boolean success;
    private LocalDateTime time;

}
