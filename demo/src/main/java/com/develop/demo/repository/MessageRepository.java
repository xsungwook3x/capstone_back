package com.develop.demo.repository;

import com.develop.demo.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity,Long> {

    List<MessageEntity> findByTownId(Long townId);
}
