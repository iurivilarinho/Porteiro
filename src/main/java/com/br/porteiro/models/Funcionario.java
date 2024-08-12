package com.br.porteiro.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbFuncionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fk_Id_Empresa")
	private Empresa empresa;
	private String cargo;
	private String horarioTrabalho;
	private String responsavel;

	@ManyToOne
	@JoinColumn(name = "fk_Id_Pessoa")
	private Pessoa pessoa;

	public Funcionario() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getHorarioTrabalho() {
		return horarioTrabalho;
	}

	public void setHorarioTrabalho(String horarioTrabalho) {
		this.horarioTrabalho = horarioTrabalho;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
