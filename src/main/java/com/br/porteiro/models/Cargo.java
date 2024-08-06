package com.br.porteiro.models;

import com.br.porteiro.form.CargoForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbCargo")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100)
	private String nome;

	@Column(length = 200)
	private String descricao;

	@Column(length = 200)
	private String departamento;

	@Column(nullable = true)
	private Boolean status;

	public Cargo() {
	}

	public Cargo(CargoForm form) {
		this.nome = form.getNome();
		this.descricao = form.getDescricao();
		this.departamento = form.getDepartamento();
		this.status = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

}
