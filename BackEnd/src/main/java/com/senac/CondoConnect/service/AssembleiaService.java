package com.senac.CondoConnect.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.CondoConnect.Model.AssembleiaModel;
import com.senac.CondoConnect.repository.AssembleiaRepository;
@Service
public class AssembleiaService {

		@Autowired
	    AssembleiaRepository assembleiaRepository;
	    public List<AssembleiaModel> findAll(){ // vai retornar uma lista de Posts
	        return assembleiaRepository.findAll();
	    }
	    public Optional<AssembleiaModel> findById(int id){ // vai retornar um unico   // Post passando o id
	        return assembleiaRepository.findById(id);
	    }
	    public AssembleiaModel save(AssembleiaModel post){ // vai salvar um Post no Banco
	        return assembleiaRepository.save(post);
	    }
	    public void delete(AssembleiaModel post){ // vai excluir um post tem menu de contexto
	    	assembleiaRepository.delete(post);
	    }
	    public List<AssembleiaModel> getListAssembleia(){
	    	return assembleiaRepository.getListAssembleia();
	    }
	    public List<AssembleiaModel> getAssembleiaUser(int id){
	    	return assembleiaRepository.getAssembleiaUser(id);
	    }
}
