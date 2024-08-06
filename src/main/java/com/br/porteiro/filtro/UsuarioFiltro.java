package com.br.porteiro.filtro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.br.porteiro.filtro.specification.UsuarioSpecification;
import com.br.porteiro.models.Usuario;
import com.br.porteiro.repository.UsuarioRepository;

@Service
public class UsuarioFiltro {
	@Autowired
	private UsuarioRepository usuarioRepositoryRepository;

	public Page<Usuario> filtro(Long idCargo, Boolean status, String search, Pageable paginacao) {

		Specification<Usuario> spec = Specification.where(null);

		if (idCargo != null) {
			spec = spec.and(UsuarioSpecification.idCargoIgual(idCargo));
		}

		if (status != null) {
			spec = spec.and(UsuarioSpecification.statusIgual(status));
		}

		if (search != null) {
			spec = spec.and(UsuarioSpecification.searchAllFields(search));
		}

		Page<Usuario> usuarioPage = usuarioRepositoryRepository.findAll(spec, paginacao);
		return usuarioPage;
	}

}
