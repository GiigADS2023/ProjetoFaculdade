package com.api.ConectApi.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.ConectApi.Model.UsuarioModel;
import com.api.ConectApi.dtos.UsuarioRecord;
import com.api.ConectApi.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioservice;
	
	@PostMapping(value ="/newuser") //retorna 201
	public ResponseEntity<Object> savePost(@RequestBody @Valid UsuarioRecord usuariodto) {
		
		var usermodel = new UsuarioModel();
		BeanUtils.copyProperties(usuariodto, usermodel);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioservice.save(usermodel));
	}
	@GetMapping(value = "/post")
	public ResponseEntity<List<UsuarioModel>> getPosts(){
		List<UsuarioModel> user = usuarioservice.findAll();
		
		if (user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
			
		}
			return ResponseEntity.status(HttpStatus.OK).body(user);
	}
}
