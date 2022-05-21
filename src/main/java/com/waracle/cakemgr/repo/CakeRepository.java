package com.waracle.cakemgr.repo;

import com.waracle.cakemgr.entity.CakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CakeRepository extends JpaRepository<CakeEntity, Long> {

    List<CakeEntity> findAll();

    CakeEntity findByTitle(String title);
}

