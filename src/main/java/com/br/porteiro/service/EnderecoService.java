package com.br.porteiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.porteiro.form.EnderecoForm;
import com.br.porteiro.models.Endereco;
import com.br.porteiro.repository.EnderecoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Transactional
	public Endereco cadastrar(EnderecoForm enderecoForm) {
		Endereco endereco = new Endereco(enderecoForm.getCep(), enderecoForm.getLogradouro(), enderecoForm.getNumero(),
				enderecoForm.getComplemento(), enderecoForm.getBairro(), enderecoForm.getCidade(),
				enderecoForm.getEstado());
		return enderecoRepository.save(endereco);
	}

	@Transactional(readOnly = true)
	public Endereco buscarEnderecoPorId(Long id) {
		return enderecoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado para o ID " + id));
	}

	@Transactional(readOnly = true)
	public List<Endereco> buscar() {
		return enderecoRepository.findAll();
	}

	@Transactional
	public Endereco atualizar(EnderecoForm enderecoForm, Long id) {
		Endereco enderecoExistente = buscarEnderecoPorId(id);
		enderecoExistente.setCep(enderecoForm.getCep());
		enderecoExistente.setLogradouro(enderecoForm.getLogradouro());
		enderecoExistente.setNumero(enderecoForm.getNumero());
		enderecoExistente.setComplemento(enderecoForm.getComplemento());
		enderecoExistente.setBairro(enderecoForm.getBairro());
		enderecoExistente.setCidade(enderecoForm.getCidade());
		enderecoExistente.setEstado(enderecoForm.getEstado());
		return enderecoRepository.save(enderecoExistente);
	}

	@Transactional
	public void deletar(Long id) {
		Endereco endereco = buscarEnderecoPorId(id);
		enderecoRepository.delete(endereco);
	}
}
