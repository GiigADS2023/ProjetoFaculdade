package com.senac.CondoConnect.Model;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name="Comunicado")
public class ComunicadoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(nullable= false)
	private String tituloComunicado;
	@Column(nullable= false)
	private String descricaoComunicado;
	@Column(nullable= false)
	private boolean tipoComunicado;
	@Column
	private LocalDate dataComunicado;
	@ManyToOne
	private UsuarioModel usuario;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String gettituloComunicado() {
		return tituloComunicado;
	}
	public void settituloComunicado(String tituloComunicado) {
		this.tituloComunicado = tituloComunicado;
	}
	public String getdescricaoComunicado() {
		return descricaoComunicado;
	}
	public void setdescricaoComunicado(String descricaoComunicado) {
		this.descricaoComunicado = descricaoComunicado;
	}
	public boolean istipoComunicado() {
		return tipoComunicado;
	}
	public void settipoComunicado(boolean tipoComunicado) {
		this.tipoComunicado = tipoComunicado;
	}
	public UsuarioModel getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}
	public LocalDate getDataComunicado() {
		return dataComunicado;
	}
	public void setDataComunicado(LocalDate dataComunicado) {
		this.dataComunicado = dataComunicado;
	}
	
	
}
