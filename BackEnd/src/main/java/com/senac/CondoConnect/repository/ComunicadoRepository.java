package com.senac.CondoConnect.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.senac.CondoConnect.Model.ComunicadoModel;

public interface ComunicadoRepository extends JpaRepository<ComunicadoModel, UUID>{

	@Query(nativeQuery = true, value = "select * from comunicado order by id desc limit 5")
    List<ComunicadoModel> getListComunicado();
	
	@Query(nativeQuery = true, value = "select * from comunicado where id = :id_re")
	Optional<ComunicadoModel> findById(int id_re);

	@Query(nativeQuery = true, value = "select * from comunicado where usuario_id = :id_re")
	List<ComunicadoModel> findByUser(int id_re);
}
