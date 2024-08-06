package com.br.porteiro.filtro.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.br.porteiro.models.Usuario;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.metamodel.Attribute;

public class UsuarioSpecification {

	public static Specification<Usuario> statusIgual(Boolean status) {
		return (root, query, builder) -> builder.equal(root.get("status"), status);
	}

	public static Specification<Usuario> idCargoIgual(Long idCargo) {
		return (root, query, builder) -> builder.equal(root.get("cargo").get("id"), idCargo);
	}

	public static Specification<Usuario> searchAllFields(String searchTerm) {
		return (root, query, criteriaBuilder) -> {
			// Lista de expressões para armazenar as condições para cada campo
			List<Predicate> predicates = new ArrayList<>();

			// Obtém todos os atributos da entidade
			for (Attribute<?, ?> attribute : root.getModel().getAttributes()) {
				if (attribute.getJavaType().equals(String.class)) {
					// Se o atributo for do tipo String, adicione uma condição de like
					predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(attribute.getName())),
							"%" + searchTerm.toLowerCase() + "%"));
				}
			}

			// Cria uma condição OR com as expressões de cada campo
			return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
		};
	}

}
