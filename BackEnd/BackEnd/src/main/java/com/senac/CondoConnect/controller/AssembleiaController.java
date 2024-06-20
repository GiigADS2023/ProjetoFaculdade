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

import com.senac.CondoConnect.Model.AssembleiaModel;
import com.senac.CondoConnect.Model.UsuarioModel;
import com.senac.CondoConnect.dtos.AssembleiaRecord;
import com.senac.CondoConnect.service.AssembleiaService;
import com.senac.CondoConnect.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class AssembleiaController {

    @Autowired
    AssembleiaService assembleiaservice;

    @Autowired
    UsuarioService usuarioservice;

    @PostMapping(value = "/newassembleia/{id}")
    public ResponseEntity<Object> saveAssembleia(@RequestBody @Valid AssembleiaRecord assembleiadto, @PathVariable("id") int id) {
        Optional<UsuarioModel> usuariomodel = usuarioservice.findById(id);
        if (!usuariomodel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        
        var assembleiamodel = new AssembleiaModel();
        BeanUtils.copyProperties(assembleiadto, assembleiamodel);
        assembleiamodel.setUsuario(usuariomodel.get());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(assembleiaservice.save(assembleiamodel));
    }

    @GetMapping(value = "/assembleia")
    public ResponseEntity<List<AssembleiaModel>> getAssembleia() {
        List<AssembleiaModel> assembleia = assembleiaservice.findAll();
        if (assembleia.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(assembleia);
        }
        return ResponseEntity.status(HttpStatus.OK).body(assembleia);
    }

    @GetMapping(value = "/assembleia/{id}")
    public ResponseEntity<Object> getAssembleiaDetails(@PathVariable("id") int id) {
        Optional<AssembleiaModel> assembleia = assembleiaservice.findById(id);
        if (!assembleia.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assembleia not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(assembleia.get());
    }

    @DeleteMapping(value = "/deleteassembleia/{id}")
    public ResponseEntity<Object> deleteAssembleia(@PathVariable("id") int id) {
        Optional<AssembleiaModel> assembleiaModelOptional = assembleiaservice.findById(id);
        if (!assembleiaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assembleia not found.");
        }
        assembleiaservice.delete(assembleiaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

    @PutMapping(value = "/putassembleia/{id}")
    public ResponseEntity<Object> putAssembleia(@RequestBody @Valid AssembleiaRecord assembleiadto, @PathVariable("id") int id) {
        Optional<AssembleiaModel> assembleiaModelOptional = assembleiaservice.findById(id);
        if (!assembleiaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assembleia not found.");
        }
        
        var assembleiaModel = assembleiaModelOptional.get();
        BeanUtils.copyProperties(assembleiadto, assembleiaModel, "id");
        
        return ResponseEntity.status(HttpStatus.OK).body(assembleiaservice.save(assembleiaModel));
    }

    @GetMapping(value = "/assembleialist")
    public ResponseEntity<List<AssembleiaModel>> getAssembleiaList() {
        List<AssembleiaModel> assembleia = assembleiaservice.getListAssembleia();
        if (assembleia.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(assembleia);
        }
        return ResponseEntity.status(HttpStatus.OK).body(assembleia);
    }

    @GetMapping(value = "/assembleialistuser/{id}")
    public ResponseEntity<List<AssembleiaModel>> getAssembleiaUser(@PathVariable("id") int id) {
        List<AssembleiaModel> assembleia = assembleiaservice.getAssembleiaUser(id);
        if (assembleia.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(assembleia);
        }
        return ResponseEntity.status(HttpStatus.OK).body(assembleia);
    }
}
