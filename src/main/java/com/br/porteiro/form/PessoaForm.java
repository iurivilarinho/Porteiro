package com.br.porteiro.form;

import java.time.LocalDate;

import com.br.porteiro.models.Endereco;
import com.br.porteiro.models.InformacaoSeguranca;

public class PessoaForm {

	private String nomeCompleto;
	private LocalDate dataNascimento;
	private String cpf;
	private String rg;
	private String genero;
	private String estadoCivil;
	private String telefoneCelular;
	private String telefoneResidencial;
	private String email;
	private Endereco endereco;
	private InformacaoSeguranca informacaoSeguranca;

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public String getGenero() {
		return genero;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public String getEmail() {
		return email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public InformacaoSeguranca getInformacaoSeguranca() {
		return informacaoSeguranca;
	}

}
