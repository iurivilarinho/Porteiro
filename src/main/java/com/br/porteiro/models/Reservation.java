package com.br.porteiro.models;

import java.util.ArrayList;
import java.util.List;

import com.br.porteiro.form.ReservationForm;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbReservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "reservation", cascade = CascadeType.MERGE)
	private List<Cota> quotas = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "fk_Id_User")
	private Pessoa userPurchase;

	public Reservation() {

	}

	public Reservation(ReservationForm form) {
		this.userPurchase = form.getUserPurchase();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Cota> getQuotas() {
		return quotas;
	}

	public void setQuotas(List<Cota> quotas) {
		this.quotas = quotas;
	}

	public Pessoa getUserPurchase() {
		return userPurchase;
	}

	public void setUserPurchase(Pessoa userPurchase) {
		this.userPurchase = userPurchase;
	}

}
