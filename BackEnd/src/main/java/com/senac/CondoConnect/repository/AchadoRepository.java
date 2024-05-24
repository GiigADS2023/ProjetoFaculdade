package com.senac.CondoConnect.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.senac.CondoConnect.Model.AchadoModel;

public interface AchadoRepository extends JpaRepository<AchadoModel, UUID>{

    @Query(nativeQuery = true, value = "select * from achado order by id desc limit 5")
    List<AchadoModel> getListAchado();
	
	@Query(nativeQuery = true, value = "select * from achado where id = :id_re")
	Optional<AchadoModel> findById(int id_re);

    @Query(nativeQuery = true, value = "select * from achado where usuario_id = :id_re")
	List<AchadoModel> findByUser(int id_re);
}
