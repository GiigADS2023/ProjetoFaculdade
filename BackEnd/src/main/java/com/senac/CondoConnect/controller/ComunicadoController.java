package com.senac.CondoConnect.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.senac.CondoConnect.Model.AssembleiaModel;
import com.senac.CondoConnect.Model.ComunicadoModel;
import com.senac.CondoConnect.dtos.ComunicadoRecord;
import com.senac.CondoConnect.service.ComunicadoService;

import jakarta.validation.Valid;

@RestController
public class ComunicadoController {

	@Autowired
	ComunicadoService comunicadoservice; 
	
	@PostMapping(value ="/newcomunicado") //retorna 201
	public ResponseEntity<Object> saveComunicado(@RequestBody @Valid ComunicadoRecord comunicadodto) {
		
		var comunicadomodel = new ComunicadoModel();
		BeanUtils.copyProperties(comunicadodto, comunicadomodel);
		comunicadomodel.setDataComunicado(LocalDate.now());
		return ResponseEntity.status(HttpStatus.CREATED).body(comunicadoservice.save(comunicadomodel));
	}
	
	@GetMapping(value = "/comunicado")
	public ResponseEntity<List<ComunicadoModel>> getComunicado(){
		List<ComunicadoModel> comunicado = comunicadoservice.findAll();
		
		if (comunicado.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(comunicado);
			
		}
			return ResponseEntity.status(HttpStatus.OK).body(comunicado);
	}
	
	@GetMapping(value ="/comunicado/{id}")
	public ResponseEntity<Object> getComunicadoDetails(@PathVariable("id") UUID id) {
		Optional<ComunicadoModel> comunicado = comunicadoservice.findById(id);
		
		if(!comunicado.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("comunicado not found.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(comunicado.get());
	}
	
	@DeleteMapping(value = "/deletecomunicado/{id}")
	public ResponseEntity<Object> deleteComunicado(@PathVariable("id") UUID id ){
		Optional<ComunicadoModel> blogappModelOptional = comunicadoservice.findById(id);
		
		if(!blogappModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("comunicado not found.");
		}
		comunicadoservice.delete(blogappModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Deleted sucefully");
	}
	
	@PutMapping(value ="/putcomunicado/{id}")
	public ResponseEntity<Object> putComunicado(@RequestBody @Valid ComunicadoRecord comunicadodto,@PathVariable("id") UUID id){
		Optional<ComunicadoModel> blogappModelOptional = comunicadoservice.findById(id);

		if(!blogappModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("comunicado not found.");
		}
		var comunicadoModel = new ComunicadoModel();
		BeanUtils.copyProperties(comunicadodto, comunicadoModel);
		comunicadoModel.setId(blogappModelOptional.get().getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(comunicadoservice.save(comunicadoModel));
	}
	@GetMapping(value = "/achadolist")
	public ResponseEntity<List<ComunicadoModel>> getAssembleiaList(){
		List<ComunicadoModel> comunicado = comunicadoservice.getListComunicado();
		
		if (comunicado.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(comunicado);
			
		}
			return ResponseEntity.status(HttpStatus.OK).body(comunicado);
	}
}
