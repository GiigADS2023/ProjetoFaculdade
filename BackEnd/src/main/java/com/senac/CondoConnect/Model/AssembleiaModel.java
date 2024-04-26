package com.senac.CondoConnect.Model;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name="Assembleia")
public class AssembleiaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column
	private String titulo;
	@Column
	private String descricao;
	@Column
	private Date data;
	@ManyToOne
	private UsuarioModel usuario;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public UsuarioModel getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	
}
