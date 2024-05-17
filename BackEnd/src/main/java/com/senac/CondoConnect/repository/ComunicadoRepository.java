package com.senac.CondoConnect.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.senac.CondoConnect.Model.AssembleiaModel;
import com.senac.CondoConnect.Model.ComunicadoModel;
import com.senac.CondoConnect.Model.ReservaModel;

public interface ComunicadoRepository extends JpaRepository<ComunicadoModel, UUID>{

	@Query(nativeQuery = true, value = "select * from comunicado where tipo_comunicado = 1 order by data_comunicado desc limit 5")
    List<ComunicadoModel> getListComunicado();
	
	@Query(nativeQuery = true, value = "call AchadoList()")
    List<ComunicadoModel> getListAchado();
	
	@Query(nativeQuery = true, value = "select * from comunicado where usuario_id = :id and tipo_comunicado = 0")
    List<ComunicadoModel> findAchadoByUser(int id);
	
	@Query(nativeQuery = true, value = "select * from comunicado where usuario_id = :id and tipo_comunicado = 1")
    List<ComunicadoModel> findComunicadoByUser(int id);
	
	@Query(nativeQuery = true, value = "select * from comunicado where id = :id_re")
	Optional<ComunicadoModel> findById(int id_re);
}
