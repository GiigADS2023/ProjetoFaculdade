package com.api.ConectApi.dtos;

import java.util.Date;

public record ReservaRecord(String comentario, Date dataInicio, Date dataFinal, UsuarioRecord usuario, EspacoRecord espaco) {

}
