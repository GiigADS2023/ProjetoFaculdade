package com.senac.CondoConnect.dtos;

import java.util.Date;

public record ReservaRecord(String comentario, Date data, UsuarioRecord usuario, String espaco) {

}
