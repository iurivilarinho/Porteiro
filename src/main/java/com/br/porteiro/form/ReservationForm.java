package com.br.porteiro.form;

import java.util.ArrayList;
import java.util.List;

import com.br.porteiro.models.UserPurchase;

public class ReservationForm {

	private Long rifaId;

	private List<Long> quotasId = new ArrayList<>();

	private UserPurchase userPurchase;

	public Long getRifaId() {
		return rifaId;
	}

	public List<Long> getQuotasId() {
		return quotasId;
	}

	public UserPurchase getUserPurchase() {
		return userPurchase;
	}

}
