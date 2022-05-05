package com.develop.demo.repository;

import com.develop.demo.model.ManagerEntity;
import com.develop.demo.model.TownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity,Long> {
    List<ManagerEntity> findByTownId(Long townId);

    Optional<ManagerEntity> findById(Long id);
}
