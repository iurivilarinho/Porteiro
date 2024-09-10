package com.br.porteiro.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.br.porteiro.form.RifaForm;
import com.br.porteiro.models.Cota;
import com.br.porteiro.models.Rifa;
import com.br.porteiro.repository.RifaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RifaService {

	@Autowired
	private RifaRepository rifaRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DocumentoService documentoService;

	@Transactional(readOnly = true)
	public List<Rifa> findAll() {
		return rifaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Rifa findById(Long id) {
		return rifaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Rifa não encontrada para ID " + id));
	}

	@Transactional(readOnly = true)
	public List<Rifa> findByCpfUser(String cpf) {
		return rifaRepository.findByCotas_Reservation_UserPurchase_Cpf(cpf);
	}

	@Transactional
	public Rifa save(RifaForm form, List<MultipartFile> files) throws IOException {
		List<Cota> cotas = new ArrayList<>();
		Rifa rifa = new Rifa(form,
				documentoService.converterEmListaDocumento(files.stream().collect(Collectors.toSet())),
				usuarioService.usuarioLogado());

		for (long i = 1; i <= form.getNumberOfShares(); i++) {
			cotas.add(new Cota(rifa, i, false));
		}

		rifa.getCotas().addAll(cotas);

		return rifaRepository.save(rifa);
	}

	@Transactional
	public Rifa update(Long id, RifaForm form, List<MultipartFile> files) throws IOException {

		List<Cota> cotas = new ArrayList<>();

		Rifa rifa = findById(id);

		rifa = form.updateRifa(rifa);
		if (files != null) {
			rifa.getImages().clear();
			rifa.getImages()
					.addAll(documentoService.converterEmListaDocumento(files.stream().collect(Collectors.toSet())));
		}

		for (long i = 1; i <= form.getNumberOfShares(); i++) {
			cotas.add(new Cota(rifa, i, false));
		}

		rifa.getCotas().addAll(cotas);

		return rifaRepository.save(rifa);
	}

	public void delete(Long id) {
		Rifa rifa = findById(id);
		rifaRepository.delete(rifa);
	}
}
