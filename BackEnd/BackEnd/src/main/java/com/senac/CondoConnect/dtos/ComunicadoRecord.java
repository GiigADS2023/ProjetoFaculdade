package com.senac.CondoConnect.dtos;

import java.time.LocalDate;

import com.senac.CondoConnect.Model.UsuarioModel;

public record ComunicadoRecord(String tituloComunicado, String descricaoComunicado, boolean tipoComunicado, LocalDate dataComunicado, UsuarioRecord usuario) {

}
