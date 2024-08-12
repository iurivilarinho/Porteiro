package com.br.porteiro.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.porteiro.form.PessoaForm;
import com.br.porteiro.models.Pessoa;
import com.br.porteiro.repository.PessoaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Transactional(readOnly = true)
	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Pessoa findById(Long id) {
		return pessoaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada para ID " + id));
	}

	@Transactional
	public Pessoa save(PessoaForm form) {
		return pessoaRepository.save(new Pessoa(form));
	}

	@Transactional
	public Pessoa update(Long id, PessoaForm pessoaDetails) {
		Pessoa pessoa = findById(id); // Reutilizando método findById

		pessoa.setNomeCompleto(pessoaDetails.getNomeCompleto());
		pessoa.setDataNascimento(pessoaDetails.getDataNascimento());
		pessoa.setCpf(pessoaDetails.getCpf());
		pessoa.setRg(pessoaDetails.getRg());
		pessoa.setGenero(pessoaDetails.getGenero());
		pessoa.setEstadoCivil(pessoaDetails.getEstadoCivil());
		pessoa.setTelefoneCelular(pessoaDetails.getTelefoneCelular());
		pessoa.setTelefoneResidencial(pessoaDetails.getTelefoneResidencial());
		pessoa.setEmail(pessoaDetails.getEmail());

		// Atualizando os campos relacionados
		if (pessoaDetails.getEndereco() != null) {
			pessoa.setEndereco(pessoaDetails.getEndereco());
		}
		if (pessoaDetails.getInformacaoSeguranca() != null) {
			pessoa.setInformacaoSeguranca(pessoaDetails.getInformacaoSeguranca());
		}

		return pessoaRepository.save(pessoa);
	}

	public void delete(Long id) {
		Pessoa pessoa = findById(id); // Reutilizando método findById
		pessoaRepository.delete(pessoa);
	}

	public void deactivate(Long id) {
		Pessoa pessoa = findById(id); // Reutilizando método findById
		pessoa.getInformacaoSeguranca().setDataSaida(LocalDate.now());
		pessoaRepository.save(pessoa);
	}
}
