package com.senac.CondoConnect.dtos;

import java.util.Date;

public record ReservaRecord(String comentario, Date dataInicio, Date dataFinal, UsuarioRecord usuario, EspacoRecord espaco) {

}
