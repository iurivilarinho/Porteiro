package com.br.porteiro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbCotas")
public class Cota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "fk_Id_Rifa")
	private Rifa rifa;

	private Long number;

	private Boolean sold;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "fk_Id_Reservation")
	private Reservation reservation;

	public Cota() {

	}

	public Cota(Rifa rifa, Long number, Boolean sold) {

		this.rifa = rifa;
		this.number = number;
		this.sold = sold;
	}

	public Long getId() {
		return id;
	}

	public Rifa getRifa() {
		return rifa;
	}

	public void setRifa(Rifa rifa) {
		this.rifa = rifa;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Boolean getSold() {
		return sold;
	}

	public void setSold(Boolean sold) {
		this.sold = sold;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

}
