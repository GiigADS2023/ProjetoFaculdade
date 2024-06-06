package com.senac.CondoConnect.dtos;

import com.senac.CondoConnect.Model.UsuarioModel;

import jakarta.validation.constraints.NotBlank;

import java.sql.Date;

public record AchadoRecord(@NotBlank String descricaoAchado, @NotBlank String tituloAchado, Date data, UsuarioModel usuario) {

}
