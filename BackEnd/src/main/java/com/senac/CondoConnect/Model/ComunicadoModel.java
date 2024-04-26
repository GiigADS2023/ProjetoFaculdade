package com.senac.CondoConnect.Model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
	@ManyToOne
	private UsuarioModel usuario;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getTituloComunicado() {
		return tituloComunicado;
	}
	public void setTituloComunicado(String tituloComunicado) {
		this.tituloComunicado = tituloComunicado;
	}
	public String getDescricaoComunicado() {
		return descricaoComunicado;
	}
	public void setDescricaoComunicado(String descricaoComunicado) {
		this.descricaoComunicado = descricaoComunicado;
	}
	public boolean isTipoComunicado() {
		return tipoComunicado;
	}
	public void setTipoComunicado(boolean tipoComunicado) {
		this.tipoComunicado = tipoComunicado;
	}
	public UsuarioModel getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}
	
	
}
