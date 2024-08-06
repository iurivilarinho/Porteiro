package com.br.porteiro.form;

import jakarta.validation.constraints.NotBlank;

public class CargoForm {

	@NotBlank(message = "O campo 'cargo' não pode ser nulo")
	private String nome;

	@NotBlank(message = "O campo 'descricao' não pode ser nulo")
	private String descricao;

	@NotBlank(message = "O campo 'departamento' não pode ser nulo")
	private String departamento;

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

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
}
