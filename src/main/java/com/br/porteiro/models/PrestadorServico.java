package com.br.porteiro.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbPrestadoresServico")
public class PrestadorServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricaoServico;
	private LocalDate dataInicio;
	private LocalDate dataFim;

	@OneToOne
	@JoinColumn(name = "fk_Id_Pessoa")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "fk_Id_Empresa")
	private Empresa empresa;

	public PrestadorServico() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
