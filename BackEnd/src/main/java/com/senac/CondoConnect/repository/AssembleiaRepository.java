package com.senac.CondoConnect.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.senac.CondoConnect.Model.AssembleiaModel;

public interface AssembleiaRepository extends JpaRepository<AssembleiaModel, UUID>{

	@Query(nativeQuery = true, value = "call AssembleiaList()")
    List<AssembleiaModel> getListAssembleia();
}
