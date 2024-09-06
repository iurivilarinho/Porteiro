package com.br.porteiro.form;

import com.br.porteiro.models.Rifa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RifaForm {

	@NotBlank(message = "O título é obrigatório.")
	private String title;

	@NotBlank(message = "A descrição é obrigatória.")
	private String description;

	@NotNull(message = "O valor da Cota é obrigatorio")
	private Float quotaPrice;

	@NotBlank(message = "O numero de Cotas não pode ser vazio!")
	private Long numberOfShares;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getNumberOfShares() {
		return numberOfShares;
	}

	public void setNumberOfShares(Long numberOfShares) {
		this.numberOfShares = numberOfShares;
	}

	public Float getQuotaPrice() {
		return quotaPrice;
	}

	public void setQuotaPrice(Float quotaPrice) {
		this.quotaPrice = quotaPrice;
	}

	// Método para atualizar uma Rifa existente
	public Rifa updateRifa(Rifa rifa) {
		rifa.setTitle(this.title);
		rifa.setDescription(this.description);
		return rifa;
	}
}
