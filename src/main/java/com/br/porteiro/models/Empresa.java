package com.br.porteiro.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbEmpresas")
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String cnpj;
	private String telefone;
	private String email;
	private String endereco;

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private Set<Funcionario> funcionarios = new HashSet<>();

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private Set<PrestadorServico> prestadoresServico = new HashSet<>();

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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Set<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Set<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Set<PrestadorServico> getPrestadoresServico() {
		return prestadoresServico;
	}

	public void setPrestadoresServico(Set<PrestadorServico> prestadoresServico) {
		this.prestadoresServico = prestadoresServico;
	}

}
