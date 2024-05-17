package com.senac.CondoConnect.dtos;

import java.sql.Date;
import java.sql.Time;

import com.senac.CondoConnect.Model.UsuarioModel;

public record AssembleiaRecord(String titulo, String descricao, Date data, UsuarioModel usuario, Time hora) {

}
