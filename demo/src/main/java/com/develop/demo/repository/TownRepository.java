package com.develop.demo.repository;


import com.develop.demo.model.TownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<TownEntity,Long> {

    List<TownEntity> findAll();

    List<TownEntity> findByUserId(Long userId);


}
