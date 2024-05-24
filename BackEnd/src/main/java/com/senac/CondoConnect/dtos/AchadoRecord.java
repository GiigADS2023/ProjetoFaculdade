package com.senac.CondoConnect.dtos;

import com.senac.CondoConnect.Model.UsuarioModel;

import jakarta.validation.constraints.NotBlank;

public record AchadoRecord(@NotBlank String descricaoAchado, @NotBlank String tituloAchado, UsuarioModel usuario) {

}
