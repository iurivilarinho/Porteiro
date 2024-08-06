package com.br.porteiro.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PerfilForm {

	@NotBlank(message = "O campo nome não pode ser vazio.")
	@Size(max = 255, message = "O nome do perfil deve ter no máximo 255 caracteres.")
	private String nome;

	public String getNome() {
		return nome;
	}

}
