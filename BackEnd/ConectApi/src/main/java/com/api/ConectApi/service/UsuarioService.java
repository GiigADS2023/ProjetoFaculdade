package com.api.ConectApi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ConectApi.Model.UsuarioModel;
import com.api.ConectApi.repository.UsuarioRepository;
@Service
public class UsuarioService {
	
	 @Autowired
	    UsuarioRepository usuarioRepository;
	    public List<UsuarioModel> findAll(){ // vai retornar uma lista de Posts
	        return usuarioRepository.findAll();
	    }
	    public Optional<UsuarioModel> findById(UUID id){ // vai retornar um unico   // Post passando o id
	        return usuarioRepository.findById(id);
	    }
	    public UsuarioModel save(UsuarioModel post){ // vai salvar um Post no Banco
	        return usuarioRepository.save(post);
	    }
	    public void delete(UsuarioModel post){ // vai excluir um post tem menu de contexto
	    	usuarioRepository.delete(post);
	    }

}
