package com.senac.CondoConnect.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.senac.CondoConnect.Model.EspacoModel;
import com.senac.CondoConnect.Model.ReservaModel;

public interface EspacoRepository extends JpaRepository<EspacoModel, UUID>{

	@Query(nativeQuery = true, value = "select * from espaco where id = :id_re")
	Optional<EspacoModel> findById(int id_re);
}
