package com.br.porteiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.porteiro.filtro.PerfilFiltro;
import com.br.porteiro.form.PerfilForm;
import com.br.porteiro.models.Perfil;
import com.br.porteiro.repository.PerfilRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private PerfilFiltro perfilFiltro;

	@Transactional
	public Perfil cadastrar(PerfilForm form) {
		return perfilRepository.save(new Perfil(form.getNome()));
	}

	@Transactional(readOnly = true)
	public Perfil buscarPerfilPorId(Long id) {
		return perfilRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado para ID " + id));
	}

	@Transactional(readOnly = true)
	public List<Perfil> buscar(Boolean status) {
		return perfilFiltro.filtro(status);
	}

	@Transactional
	public Perfil atualizar(PerfilForm form, Long id) {
		Perfil perfil = buscarPerfilPorId(id);
		perfil.setNome(form.getNome());
		return perfilRepository.save(perfil);
	}

	@Transactional
	public void ativarDesativar(Long idCargo, Boolean status) {
		Perfil perfil = buscarPerfilPorId(idCargo);
		perfil.setStatus(status);
		perfilRepository.save(perfil);
	}
}
