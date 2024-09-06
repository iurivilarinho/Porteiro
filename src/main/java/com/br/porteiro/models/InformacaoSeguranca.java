package com.br.porteiro.models;

import java.time.LocalDate;

import com.br.porteiro.enums.TiposDeTipos.TiposPessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbInformacoesSeguranca")
public class InformacaoSeguranca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String codigoAcesso;
	@Enumerated(EnumType.STRING)
	private TiposPessoa tipoPessoa;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;
	private String placaVeiculo;
	private Boolean autorizacaoEntradaVisitantes;
	private String nomeContatoEmergencia;
	private String relacaoContatoEmergencia;
	private String telefoneContatoEmergencia;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "fk_Id_Pessoa", foreignKey = @ForeignKey(name = "FK_FROM_TBPESSOA_FOR_TBINFORMACOESSEGURANCA"))
	private Pessoa pessoa;

	public InformacaoSeguranca() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoAcesso() {
		return codigoAcesso;
	}

	public void setCodigoAcesso(String codigoAcesso) {
		this.codigoAcesso = codigoAcesso;
	}

	public TiposPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TiposPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getPlacaVeiculo() {
		return placaVeiculo;
	}

	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}

	public Boolean getAutorizacaoEntradaVisitantes() {
		return autorizacaoEntradaVisitantes;
	}

	public void setAutorizacaoEntradaVisitantes(Boolean autorizacaoEntradaVisitantes) {
		this.autorizacaoEntradaVisitantes = autorizacaoEntradaVisitantes;
	}

	public String getNomeContatoEmergencia() {
		return nomeContatoEmergencia;
	}

	public void setNomeContatoEmergencia(String nomeContatoEmergencia) {
		this.nomeContatoEmergencia = nomeContatoEmergencia;
	}

	public String getRelacaoContatoEmergencia() {
		return relacaoContatoEmergencia;
	}

	public void setRelacaoContatoEmergencia(String relacaoContatoEmergencia) {
		this.relacaoContatoEmergencia = relacaoContatoEmergencia;
	}

	public String getTelefoneContatoEmergencia() {
		return telefoneContatoEmergencia;
	}

	public void setTelefoneContatoEmergencia(String telefoneContatoEmergencia) {
		this.telefoneContatoEmergencia = telefoneContatoEmergencia;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
