package com.api.ConectApi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ConectApi.Model.AssembleiaModel;

public interface AssembleiaRepository extends JpaRepository<AssembleiaModel, UUID>{

}
