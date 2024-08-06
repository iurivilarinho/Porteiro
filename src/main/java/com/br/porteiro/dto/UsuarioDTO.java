package com.br.porteiro.dto;

import java.util.HashSet;
import java.util.Set;

import com.br.porteiro.models.Cargo;
import com.br.porteiro.models.Perfil;
import com.br.porteiro.models.Usuario;

public class UsuarioDTO {

	private Long id;
	private String login;
	private String nome;
	private String senha;
	private String cpf;
	private String celularCorporativo;
	private String celularPessoal;
	private String email;
	private Boolean status;
	private Long idEmpresa;
	private Cargo cargo;
	private Set<Perfil> perfis = new HashSet<>();

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.login = usuario.getLogin();
		this.nome = usuario.getNome();
		this.senha = usuario.getSenha();
		this.cpf = usuario.getCpf();
		this.celularCorporativo = usuario.getCelularCorporativo();
		this.celularPessoal = usuario.getCelularPessoal();
		this.email = usuario.getEmail();
		this.status = usuario.getStatus();
		this.perfis = usuario.getPerfis();

		this.cargo = usuario.getCargo();
	}

	public String getLogin() {
		return login;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public String getCpf() {
		return cpf;
	}

	public String getCelularCorporativo() {
		return celularCorporativo;
	}

	public String getCelularPessoal() {
		return celularPessoal;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getStatus() {
		return status;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public Long getId() {
		return id;
	}

	public Cargo getCargo() {
		return cargo;
	}

}
