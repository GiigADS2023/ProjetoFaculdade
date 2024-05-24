package com.senac.CondoConnect.Model;


import java.util.Date;

import jakarta.persistence.*;


@Entity
@Table(name="Reserva")
public class ReservaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable= false)
	private String comentario;
	@ManyToOne
	private UsuarioModel usuario;
	@ManyToOne
	private EspacoModel espaco;
	@Column(nullable= false)
	private Date dataInicio;
	@Column(nullable= false)
	private Date dataFinal;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public UsuarioModel getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}
	public EspacoModel getEspaco() {
		return espaco;
	}
	public void setEspaco(EspacoModel espaco) {
		this.espaco = espaco;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	
}
