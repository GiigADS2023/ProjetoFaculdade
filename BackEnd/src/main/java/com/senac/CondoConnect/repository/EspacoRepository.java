package com.senac.CondoConnect.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.CondoConnect.Model.EspacoModel;

public interface EspacoRepository extends JpaRepository<EspacoModel, UUID>{

}
