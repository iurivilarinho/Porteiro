package com.br.porteiro.form;

import com.br.porteiro.models.Cargo;
import com.br.porteiro.models.PaymentInformation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioForm {

	@NotNull(message = "O campo 'Login' não pode ser nulo.")
	@Size(max = 10, message = "O valor máximo de caracteres para o campo 'login' é de 10 caracteres.")
	private String login;

	@NotNull(message = "O campo 'Nome' não pode ser nulo.")
	@Size(max = 40, message = "O valor máximo de caracteres para o campo 'Nome' é de 40 caracteres.")
	private String nome;

	@NotNull(message = "O campo 'Senha' não pode ser nulo.")
	@Size(max = 8, message = "O valor máximo de caracteres para o campo 'Senha' é de 8 caracteres.")
	private String senha;

	@NotNull(message = "O campo 'CPF' não pode ser nulo.")
	@Size(max = 11, message = "O valor máximo de caracteres para o campo 'CPF' é de 11 caracteres.")
	private String cpf;

	private String celularCorporativo;

	private String celularPessoal;

	@NotNull(message = "O campo 'e-mail' não pode ser nulo.")
	@Size(max = 50, message = "O valor máximo de caracteres para o campo 'e-mail' é de 50 caracteres.")
	private String email;

	@NotNull(message = "O campo 'cargo' não pode ser nulo.")
	private Cargo cargo;

	private PaymentInformation paymentInformation;

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

	public Cargo getCargo() {
		return cargo;
	}

	public PaymentInformation getPaymentInformation() {
		return paymentInformation;
	}

}
