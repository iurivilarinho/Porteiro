package com.br.porteiro.models;

import java.time.LocalDate;

import com.br.porteiro.form.PessoaForm;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbPessoas")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nomeCompleto;
	private LocalDate dataNascimento;
	private String cpf;
	private String rg;
	private String genero;
	private String estadoCivil;
	private String telefoneCelular;
	private String telefoneResidencial;
	private String email;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_Id_Documento", foreignKey = @ForeignKey(name = "FK_FROM_TBDOCUMENTO_FOR_TBPESSOA"))
	private Documento foto;

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
	private Endereco endereco;

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
	private InformacaoSeguranca informacaoSeguranca;

	public Pessoa() {
	}

	public Pessoa(PessoaForm form, Documento foto) {
		this.nomeCompleto = form.getNomeCompleto();
		this.dataNascimento = form.getDataNascimento();
		this.cpf = form.getCpf();
		this.rg = form.getRg();
		this.genero = form.getGenero();
		this.estadoCivil = form.getEstadoCivil();
		this.telefoneCelular = form.getTelefoneCelular();
		this.telefoneResidencial = form.getTelefoneResidencial();
		this.email = form.getEmail();
		this.endereco = form.getEndereco();
		this.informacaoSeguranca = form.getInformacaoSeguranca();
		this.foto = foto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public InformacaoSeguranca getInformacaoSeguranca() {
		return informacaoSeguranca;
	}

	public void setInformacaoSeguranca(InformacaoSeguranca informacaoSeguranca) {
		this.informacaoSeguranca = informacaoSeguranca;
	}

	public Documento getFoto() {
		return foto;
	}

	public void setFoto(Documento foto) {
		this.foto = foto;
	}

}
