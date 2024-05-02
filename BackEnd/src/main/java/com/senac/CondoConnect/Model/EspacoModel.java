package com.senac.CondoConnect.Model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name="Espaco")
public class EspacoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable= false)
	private String nomeEspaco;
	@OneToMany
	private List<ReservaModel> reservalist;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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
