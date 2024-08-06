package com.br.porteiro.filtro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.br.porteiro.filtro.specification.PerfilSpecification;
import com.br.porteiro.models.Perfil;
import com.br.porteiro.repository.PerfilRepository;

@Service
public class PerfilFiltro {

	@Autowired
	private PerfilRepository perfilRepository;

	public List<Perfil> filtro(Boolean status) {
		Specification<Perfil> spec = Specification.where(null);

		if (status != null) {
			spec = spec.and(PerfilSpecification.statusIgual(status));
		}

		List<Perfil> resultados = perfilRepository.findAll(spec);

		return resultados;

	}
}
