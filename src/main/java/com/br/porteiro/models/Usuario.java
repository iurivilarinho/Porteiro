package com.br.porteiro.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.br.porteiro.form.UsuarioForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbUsuario", indexes = { @Index(name = "login", columnList = "login"),
		@Index(name = "email", columnList = "email") })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 14, nullable = false)
	private String cpf;

	@Column(length = 100, nullable = false)
	private String login;

	@Column(length = 200, nullable = false)
	private String senha;

	@Column(length = 100, nullable = false)
	private String email;

	private String celularCorporativo;

	private String celularPessoal;

	@ManyToOne
	@JoinColumn(name = "fk_Id_Imagem", foreignKey = @ForeignKey(name = "FK_FROM_TBDOCUMENTO_FOR_TBUSUARIO"))
	private Documento imagem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_Id_Cargo", foreignKey = @ForeignKey(name = "FK_FROM_TBCARGO_FOR_TBUSUARIO"))
	private Cargo cargo;

	@Column(name = "status")
	private Boolean status;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Usuario_x_Perfil", joinColumns = @JoinColumn(name = "fk_Id_Usuario"), inverseJoinColumns = @JoinColumn(name = "fk_Id_Perfil"))
	@Fetch(FetchMode.JOIN)
	private Set<Perfil> perfis = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "fk_Id_PaymentInformation")
	private PaymentInformation paymentInformation;

	public Usuario() {

	}

	public Usuario(UsuarioForm form, Documento imagem) {
		this.login = form.getLogin();
		this.nome = form.getNome();
		this.email = form.getEmail();
		this.senha = new BCryptPasswordEncoder().encode(form.getSenha());
		this.cpf = form.getCpf();
		this.celularCorporativo = form.getCelularCorporativo();
		this.celularPessoal = form.getCelularPessoal();
		this.imagem = imagem;
		this.status = true;
		this.cargo = form.getCargo();
		this.paymentInformation = form.getPaymentInformation();

	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCelularCorporativo() {
		return celularCorporativo;
	}

	public void setCelularCorporativo(String celularCorporativo) {
		this.celularCorporativo = celularCorporativo;
	}

	public String getCelularPessoal() {
		return celularPessoal;
	}

	public void setCelularPessoal(String celularPessoal) {
		this.celularPessoal = celularPessoal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Documento getImagem() {
		return imagem;
	}

	public void setImagem(Documento imagem) {
		this.imagem = imagem;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		// TODO Stub de método gerado automaticamente
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Stub de método gerado automaticamente
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Stub de método gerado automaticamente
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Stub de método gerado automaticamente
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Stub de método gerado automaticamente
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Stub de método gerado automaticamente
		return true;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public PaymentInformation getPaymentInformation() {
		return paymentInformation;
	}

	public void setPaymentInformation(PaymentInformation paymentInformation) {
		this.paymentInformation = paymentInformation;
	}

}
