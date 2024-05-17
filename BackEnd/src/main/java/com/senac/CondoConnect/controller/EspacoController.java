package com.senac.CondoConnect.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.senac.CondoConnect.Model.EspacoModel;
import com.senac.CondoConnect.dtos.EspacoRecord;
import com.senac.CondoConnect.service.EspacoService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins  = "http://localhost:5173/meeting")
public class EspacoController {

	@Autowired
	EspacoService espacoservice; 
	
	@PostMapping(value ="/newespaco") //retorna 201
	public ResponseEntity<Object> saveEspaco(@RequestBody @Valid EspacoRecord espacodto) {
		
		var espacomodel = new EspacoModel();
		BeanUtils.copyProperties(espacodto, espacomodel);
		return ResponseEntity.status(HttpStatus.CREATED).body(espacoservice.save(espacomodel));
	}
	
	@GetMapping(value = "/espaco")
	public ResponseEntity<List<EspacoModel>> getPosts(){
		List<EspacoModel> espaco = espacoservice.findAll();
		
		if (espaco.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(espaco);
			
		}
			return ResponseEntity.status(HttpStatus.OK).body(espaco);
	}
	
	@GetMapping(value ="/espaco/{id}")
	public ResponseEntity<Object> getEspacoDetails(@PathVariable("id") int id) {
		Optional<EspacoModel> espaco = espacoservice.findById(id);
		
		if(!espaco.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("espaco not found.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(espaco.get());
	}
	
	@DeleteMapping(value = "/deleteespaco/{id}")
	public ResponseEntity<Object> deleteEspaco(@PathVariable("id") int id ){
		Optional<EspacoModel> blogappModelOptional = espacoservice.findById(id);
		
		if(!blogappModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("espaco not found.");
		}
		espacoservice.delete(blogappModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Deleted sucefully");
	}
	
	@PutMapping(value ="/putespaco/{id}")
	public ResponseEntity<Object> putEspaco(@RequestBody @Valid EspacoRecord espacodto,@PathVariable("id") int id){
		Optional<EspacoModel> blogappModelOptional = espacoservice.findById(id);

		if(!blogappModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("espaco not found.");
		}
		var espacoModel = new EspacoModel();
		BeanUtils.copyProperties(espacodto, espacoModel);
		espacoModel.setId(blogappModelOptional.get().getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(espacoservice.save(espacoModel));
	}
}
