package com.senac.CondoConnect.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.CondoConnect.Model.AssembleiaModel;

public interface AssembleiaRepository extends JpaRepository<AssembleiaModel, UUID>{

}
