package com.develop.demo.repository;

import com.develop.demo.model.TerminalInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerminalInfoRepository extends JpaRepository<TerminalInfoEntity,Long> {

    List<TerminalInfoEntity> findByTerminalId(Long terminalId);
}
