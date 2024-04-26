package com.senac.CondoConnect.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.CondoConnect.Model.ComunicadoModel;

public interface ComunicadoRepository extends JpaRepository<ComunicadoModel, UUID>{

}
