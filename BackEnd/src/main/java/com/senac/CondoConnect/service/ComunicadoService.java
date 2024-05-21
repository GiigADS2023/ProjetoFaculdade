package com.senac.CondoConnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.CondoConnect.Model.ComunicadoModel;
import com.senac.CondoConnect.repository.ComunicadoRepository;
@Service
public class ComunicadoService {

	 @Autowired
	    ComunicadoRepository comunicadoRepository;
	    public List<ComunicadoModel> findAll(){ // vai retornar uma lista de Posts
	        return comunicadoRepository.findAll();
	    }
	    public Optional<ComunicadoModel> findById(int id){ // vai retornar um unico   // Post passando o id
	        return comunicadoRepository.findById(id);
	    }
	    public ComunicadoModel save(ComunicadoModel post){ // vai salvar um Post no Banco
	        return comunicadoRepository.save(post);
	    }
	    public void delete(ComunicadoModel post){ // vai excluir um post tem menu de contexto
	    	comunicadoRepository.delete(post);
	    }
	    public List<ComunicadoModel> getListComunicado(){
	    	return comunicadoRepository.getListComunicado();
	    }
		public List<ComunicadoModel> findByUser(int id){
			return comunicadoRepository.findByUser(id);
		}
}
