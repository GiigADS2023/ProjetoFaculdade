package com.senac.CondoConnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.senac.CondoConnect.Model.AchadoModel;
import com.senac.CondoConnect.repository.AchadoRepository;

@Service
public class AchadoService {
    @Autowired
    AchadoRepository achadoRepository;
    public List<AchadoModel> findAll(){ // vai retornar uma lista de Posts
        return achadoRepository.findAll();
    }
    public Optional<AchadoModel> findById(int id){ // vai retornar um unico   // Post passando o id
        return achadoRepository.findById(id);
    }
    public AchadoModel save(AchadoModel post){ // vai salvar um Post no Banco
        return achadoRepository.save(post);
    }
    public void delete(AchadoModel post){ // vai excluir um post tem menu de contexto
        achadoRepository.delete(post);
    }
    public List<AchadoModel> getListAchado(){
        return achadoRepository.getListAchado();
    }
    public List<AchadoModel> getAchadoUser(int id){
        return achadoRepository.findByUser(id);
    }
}
