package com.senac.CondoConnect.dtos;

import java.time.LocalDate;

public record AssembleiaRecord(String titulo, String descricao, LocalDate data, UsuarioRecord usuario) {

}
