package com.br.porteiro.filtro.specification;

import org.springframework.data.jpa.domain.Specification;

import com.br.porteiro.models.Perfil;

public class PerfilSpecification {

	public static Specification<Perfil> statusIgual(Boolean status) {
		return (root, query, builder) -> builder.equal(root.get("status"), status);
	}
}
