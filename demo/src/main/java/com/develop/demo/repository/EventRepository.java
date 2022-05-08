package com.develop.demo.repository;

import com.develop.demo.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity,Long> {

    List<EventEntity> findByTownId(Long townId);
}
