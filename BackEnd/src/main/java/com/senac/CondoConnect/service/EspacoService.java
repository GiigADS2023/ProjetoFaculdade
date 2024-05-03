package com.senac.CondoConnect.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.CondoConnect.Model.EspacoModel;
import com.senac.CondoConnect.repository.EspacoRepository;
@Service
public class EspacoService {
	
	 @Autowired
	    EspacoRepository espacoRepository;
	    public List<EspacoModel> findAll(){ // vai retornar uma lista de Posts
	        return espacoRepository.findAll();
	    }
	    public Optional<EspacoModel> findById(UUID id){ // vai retornar um unico   // Post passando o id
	        return espacoRepository.findById(id);
	    }
	    public EspacoModel save(EspacoModel post){ // vai salvar um Post no Banco
	        return espacoRepository.save(post);
	    }
	    public void delete(EspacoModel post){ // vai excluir um post tem menu de contexto
	    	espacoRepository.delete(post);
	    }
}
