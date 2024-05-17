package com.senac.CondoConnect.Model;

import java.util.List;


import jakarta.persistence.*;

@Entity
@Table(name="Espaco")
public class EspacoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable= false)
	private String nomeEspaco;
	@OneToMany
	private List<ReservaModel> reservalist;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeEspaco() {
		return nomeEspaco;
	}

	public void setNomeEspaco(String nomeEspaco) {
		this.nomeEspaco = nomeEspaco;
	}

	public List<ReservaModel> getReservalist() {
		return reservalist;
	}

	public void setReservalist(List<ReservaModel> reservalist) {
		this.reservalist = reservalist;
	}
	
}
