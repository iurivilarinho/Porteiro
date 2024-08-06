package com.br.porteiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.porteiro.form.CargoForm;
import com.br.porteiro.models.Cargo;
import com.br.porteiro.repository.CargoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;

	@Transactional
	public Cargo cadastrar(CargoForm form) {
		return cargoRepository.save(new Cargo(form));
	}

	@Transactional(readOnly = true)
	public Cargo buscarCargoPorId(Long id) {
		return cargoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado para ID " + id));
	}

	@Transactional(readOnly = true)
	public Page<Cargo> buscar(Boolean status, Pageable page) {
		return cargoRepository.findByStatus(status, page);
	}

	@Transactional
	public Cargo atualizar(CargoForm form, Long id) {
		Cargo cargo = buscarCargoPorId(id);
		cargo.setNome(form.getNome());
		cargo.setDescricao(form.getDescricao());
		cargo.setDepartamento(form.getDepartamento());
		return cargoRepository.save(cargo);
	}

	@Transactional
	public void ativarDesaivar(Long idCargo, Boolean status) {
		Cargo cargo = buscarCargoPorId(idCargo);
		cargo.setStatus(status);
		cargoRepository.save(cargo);
	}
}
