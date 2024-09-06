package com.br.porteiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.porteiro.models.Cota;
import com.br.porteiro.repository.CotasRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CotaService {

	@Autowired
	private CotasRepository cotaRepository;

	@Transactional(readOnly = true)
	public Cota findById(Long number, Long rifaId) {
		return cotaRepository.findByNumberAndRifa_Id(number, rifaId).orElseThrow(() -> new EntityNotFoundException(
				"Cota não encontrada para número " + number + "e rica com ID " + rifaId));
	}

}
