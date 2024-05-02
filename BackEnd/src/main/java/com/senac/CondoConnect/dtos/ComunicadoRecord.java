package com.senac.CondoConnect.dtos;

import java.time.LocalDate;

public record ComunicadoRecord(String tituloComunicado, String descricaoComunicado, boolean tipoComunicado, LocalDate dataComunicado, UsuarioRecord usuario) {

}
