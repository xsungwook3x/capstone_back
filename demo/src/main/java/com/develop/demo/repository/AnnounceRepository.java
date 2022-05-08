package com.develop.demo.repository;

import com.develop.demo.model.AnnounceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnounceRepository extends JpaRepository<AnnounceEntity,Long> {

    List<AnnounceEntity> findByTownId(Long townId);
}
