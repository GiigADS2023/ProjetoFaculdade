package com.senac.CondoConnect.Model;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name="Moradores")
public class UsuarioModel {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable=false)
	private String nomeUsuario;
	@Column(nullable=false)
	private String apartamentoUsuario;
	@Column
	private String emailUsuario;
	@Column
	private String senhaUsuario;
	@OneToMany
	private List<ComunicadoModel> comunicadolist;
	@OneToMany
	private List<ReservaModel> reservalist;

	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getApartamentoUsuario() {
		return apartamentoUsuario;
	}
	public void setApartamentoUsuario(String apartamentoUsuario) {
		this.apartamentoUsuario = apartamentoUsuario;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	public String getSenhaUsuario() {
		return senhaUsuario;
	}
	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	public List<ComunicadoModel> getComunicadolist() {
		return comunicadolist;
	}
	public void setComunicadolist(List<ComunicadoModel> comunicadolist) {
		this.comunicadolist = comunicadolist;
	}
	public List<ReservaModel> getReservalist() {
		return reservalist;
	}
	public void setReservalist(List<ReservaModel> reservalist) {
		this.reservalist = reservalist;
	}
	

}
