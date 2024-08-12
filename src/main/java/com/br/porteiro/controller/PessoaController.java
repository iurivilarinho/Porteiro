package com.br.porteiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.porteiro.form.PessoaForm;
import com.br.porteiro.models.Pessoa;
import com.br.porteiro.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	public List<Pessoa> getAllPessoas() {
		return pessoaService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
		return ResponseEntity.ok(pessoaService.findById(id));
	}

	@PostMapping
	public ResponseEntity<Pessoa> createPessoa(@RequestBody PessoaForm form) {

		Pessoa createdPessoa = pessoaService.save(form);
		return ResponseEntity.ok(createdPessoa);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody PessoaForm pessoaForm) {

		return ResponseEntity.ok(pessoaService.update(id, pessoaForm));
	}

//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
//		pessoaService.delete(id);
//		return ResponseEntity.noContent().build();
//	}

	@PutMapping("/{id}/deactivate")
	public ResponseEntity<Void> deactivatePessoa(@PathVariable Long id) {
		pessoaService.deactivate(id);
		return ResponseEntity.noContent().build();
	}
}
