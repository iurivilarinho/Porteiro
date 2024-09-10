package com.br.porteiro.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.br.porteiro.form.RifaForm;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbRifa")
public class Rifa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@Column(length = 2000)
	private String description;

	private Float quotaPrice;

	@ManyToOne(cascade = CascadeType.ALL)
	private Usuario userCreation;

	@Column(length = 2000)
	private String descriptionAward;

	@OneToMany(mappedBy = "rifa", cascade = CascadeType.ALL)
	private List<Cota> cotas = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(joinColumns = @JoinColumn(name = "fk_Id_Rifa"), inverseJoinColumns = @JoinColumn(name = "fk_Id_Document"), foreignKey = @ForeignKey(name = "FK_FROM_TBRIFA"), inverseForeignKey = @ForeignKey(name = "FK_FROM_TBDOCUMENT"))
	private Set<Documento> images = new HashSet<>();

	public Rifa() {
		super();
	}

	public Rifa(RifaForm form, Set<Documento> file, Usuario userCreation) {
		this.title = form.getTitle();
		this.description = form.getDescription();
		this.images = file;
		this.quotaPrice = form.getQuotaPrice();
		this.userCreation = userCreation;
		this.descriptionAward = form.getDescriptionAward();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Set<Documento> getImages() {
		return images;
	}

	public void setImages(Set<Documento> images) {
		this.images = images;
	}

	public List<Cota> getCotas() {
		return cotas;
	}

	public void setCotas(List<Cota> cotas) {
		this.cotas = cotas;
	}

	public Float getQuotaPrice() {
		return quotaPrice;
	}

	public void setQuotaPrice(Float quotaPrice) {
		this.quotaPrice = quotaPrice;
	}

	public Usuario getUserCreation() {
		return userCreation;
	}

	public void setUserCreation(Usuario userCreation) {
		this.userCreation = userCreation;
	}

	public String getDescriptionAward() {
		return descriptionAward;
	}

	public void setDescriptionAward(String descriptionAward) {
		this.descriptionAward = descriptionAward;
	}

}
