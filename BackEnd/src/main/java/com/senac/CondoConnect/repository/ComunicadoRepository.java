package com.senac.CondoConnect.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.senac.CondoConnect.Model.AssembleiaModel;
import com.senac.CondoConnect.Model.ComunicadoModel;

public interface ComunicadoRepository extends JpaRepository<ComunicadoModel, UUID>{

	@Query(nativeQuery = true, value = "call ComunicadoList()")
    List<ComunicadoModel> getListComunicado();
}
