package com.br.porteiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.porteiro.form.CargoForm;
import com.br.porteiro.models.Cargo;
import com.br.porteiro.service.CargoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cargos")
public class CargoController {

	@Autowired
	private CargoService cargoService;

	@PostMapping
	public ResponseEntity<Cargo> cadastrarCargo(@Valid @RequestBody CargoForm cargoForm) {
		Cargo cargo = cargoService.cadastrar(cargoForm);
		return ResponseEntity.status(HttpStatus.CREATED).body(cargo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cargo> buscarCargoPorId(@PathVariable Long id) {
		Cargo cargo = cargoService.buscarCargoPorId(id);
		return ResponseEntity.ok(cargo);
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<Page<Cargo>> buscarCargos(@PathVariable Boolean status, Pageable page) {

		Page<Cargo> cargos = cargoService.buscar(status, page);
		return ResponseEntity.ok(cargos);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cargo> atualizarCargo(@PathVariable Long id, @Valid @RequestBody CargoForm cargoForm) {
		Cargo cargoAtualizado = cargoService.atualizar(cargoForm, id);
		return ResponseEntity.ok(cargoAtualizado);
	}

	@PutMapping("/status/{status}/{id}")
	public ResponseEntity<?> ativarDesativar(@PathVariable Long id, @PathVariable Boolean status) {
		cargoService.ativarDesaivar(id, status);
		return ResponseEntity.ok().build();
	}
}
