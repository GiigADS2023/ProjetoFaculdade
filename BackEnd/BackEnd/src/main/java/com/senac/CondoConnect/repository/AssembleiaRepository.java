package com.senac.CondoConnect.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.senac.CondoConnect.Model.AssembleiaModel;
import com.senac.CondoConnect.Model.ReservaModel;

public interface AssembleiaRepository extends JpaRepository<AssembleiaModel, UUID>{

	@Query(nativeQuery = true, value = "call AssembleiaList()")
    List<AssembleiaModel> getListAssembleia();
	@Query(nativeQuery = true, value = "select * from assembleia where usuario_id = :id")
    List<AssembleiaModel> getAssembleiaUser(int id);
	@Query(nativeQuery = true, value = "select * from assembleia where id = :id_re")
	Optional<AssembleiaModel> findById(int id_re);
}
