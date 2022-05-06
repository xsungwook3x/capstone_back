package com.develop.demo.repository;

import com.develop.demo.model.TerminalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerminalRepository extends JpaRepository<TerminalEntity,Long>{

    List<TerminalEntity> findByTownId(Long id);
}
