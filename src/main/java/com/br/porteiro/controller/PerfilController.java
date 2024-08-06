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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.porteiro.form.PerfilForm;
import com.br.porteiro.models.Perfil;
import com.br.porteiro.service.PerfilService;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private PerfilService perfilService;

	@GetMapping
	public ResponseEntity<List<Perfil>> buscar(@RequestParam(required = false) Boolean status) {
		return ResponseEntity.ok(perfilService.buscar(status));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Perfil> buscarPerfilPorId(@PathVariable Long id) {
		return ResponseEntity.ok(perfilService.buscarPerfilPorId(id));
	}

	@PostMapping
	public ResponseEntity<Perfil> cadastrar(@RequestBody PerfilForm form) {
		return ResponseEntity.ok(perfilService.cadastrar(form));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Perfil> AtualizarPerfil(@PathVariable Long id, @RequestBody PerfilForm form) {
		return ResponseEntity.ok(perfilService.atualizar(form, id));
	}

	@PutMapping("/status/{status}/{id}")
	public ResponseEntity<?> ativarDesativar(@PathVariable Long id, @PathVariable Boolean status) {
		perfilService.ativarDesativar(id, status);
		return ResponseEntity.ok().build();
	}
}
