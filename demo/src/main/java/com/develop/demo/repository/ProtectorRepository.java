package com.develop.demo.repository;

import com.develop.demo.model.ProtectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface ProtectorRepository extends JpaRepository<ProtectorEntity,Long> {

    List<ProtectorEntity> findByTownId(Long townId);

    List<ProtectorEntity> findByTerminalId(Long terminalId);
}
