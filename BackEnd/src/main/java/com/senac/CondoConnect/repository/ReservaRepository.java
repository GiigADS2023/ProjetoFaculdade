package com.senac.CondoConnect.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.CondoConnect.Model.ReservaModel;

public interface ReservaRepository extends JpaRepository<ReservaModel, UUID>{

}
