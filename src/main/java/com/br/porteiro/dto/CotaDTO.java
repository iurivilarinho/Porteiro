package com.br.porteiro.dto;

import com.br.porteiro.models.Cota;

public class CotaDTO {

	private Long id;

	private Long number;

	private Long userPurchaseId;

	private Boolean sold;

	public CotaDTO(Cota cota) {

		this.id = cota.getId();
		this.number = cota.getNumber();
		this.userPurchaseId = cota.getReservation() != null ? cota.getReservation().getUserPurchase().getId() : null;
		this.sold = cota.getSold();
	}

	public Long getId() {
		return id;
	}

	public Long getNumber() {
		return number;
	}

	public Long getUserPurchaseId() {
		return userPurchaseId;
	}

	public Boolean getSold() {
		return sold;
	}

}
