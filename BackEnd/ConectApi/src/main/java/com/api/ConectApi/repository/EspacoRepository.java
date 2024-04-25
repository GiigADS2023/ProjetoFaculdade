package com.api.ConectApi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ConectApi.Model.EspacoModel;

public interface EspacoRepository extends JpaRepository<EspacoModel, UUID>{

}
