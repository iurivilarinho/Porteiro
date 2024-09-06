package com.br.porteiro.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.br.porteiro.models.Documento;
import com.br.porteiro.models.Rifa;
import com.br.porteiro.models.Usuario;

public class RifaDTO {

	private Long id;

	private String title;

	private String description;

	private Float quotaPrice;

	private Usuario userCreation;

	private List<CotaDTO> cotas = new ArrayList<>();

	private Set<Documento> images = new HashSet<>();

	public RifaDTO(Rifa rifa) {

		this.id = rifa.getId();
		this.title = rifa.getTitle();
		this.description = rifa.getDescription();
		this.quotaPrice = rifa.getQuotaPrice();
		this.userCreation = rifa.getUserCreation();
		this.cotas = rifa.getCotas().stream().map(CotaDTO::new).toList();
		this.images = rifa.getImages();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Float getQuotaPrice() {
		return quotaPrice;
	}

	public Usuario getUserCreation() {
		return userCreation;
	}

	public List<CotaDTO> getCotas() {
		return cotas;
	}

	public Set<Documento> getImages() {
		return images;
	}

}
