package com.br.porteiro.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.br.porteiro.dto.RifaDTO;
import com.br.porteiro.form.RifaForm;
import com.br.porteiro.models.Rifa;
import com.br.porteiro.service.RifaService;

@RestController
@RequestMapping("/rifas")
public class RifaController {

	@Autowired
	private RifaService rifaService;

	@GetMapping
	public ResponseEntity<List<Rifa>> findAll() {
		List<Rifa> rifas = rifaService.findAll();
		return ResponseEntity.ok(rifas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RifaDTO> findById(@PathVariable Long id) {
		Rifa rifa = rifaService.findById(id);
		return ResponseEntity.ok(new RifaDTO(rifa));
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<List<RifaDTO>> findByCpfUser(@PathVariable String cpf) {
		List<Rifa> rifas = rifaService.findByCpfUser(cpf);
		return ResponseEntity.ok(rifas.stream().map(RifaDTO::new).toList());
	}

	@PostMapping(consumes = { "multipart/form-data" })
	public ResponseEntity<Rifa> create(@RequestPart RifaForm form, @RequestPart("files") List<MultipartFile> files)
			throws IOException {
		Rifa rifa = rifaService.save(form, files);
		return ResponseEntity.ok(rifa);
	}

	@PutMapping(value = "/{id}", consumes = { "multipart/form-data" })
	public ResponseEntity<Rifa> update(@PathVariable Long id, @RequestPart RifaForm form,
			@RequestPart("files") List<MultipartFile> files) throws IOException {
		Rifa updatedRifa = rifaService.update(id, form, files);
		return ResponseEntity.ok(updatedRifa);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		rifaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
