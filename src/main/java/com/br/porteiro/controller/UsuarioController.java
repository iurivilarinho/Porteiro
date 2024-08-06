package com.br.porteiro.controller;

import java.io.IOException;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.br.porteiro.dto.UsuarioDTO;
import com.br.porteiro.form.UsuarioForm;
import com.br.porteiro.models.Usuario;
import com.br.porteiro.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping(consumes = { "multipart/form-data" })
	public ResponseEntity<UsuarioDTO> cadastrarUsuario(@Valid @RequestPart UsuarioForm form,
			@RequestPart(value = "img", required = false) MultipartFile img) throws IOException {
		Usuario usuario = usuarioService.cadastrar(form, img);
		return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioDTO(usuario));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioForm form,
			@RequestParam("img") MultipartFile img) throws IOException {
		Usuario usuario = usuarioService.atualizar(id, form, img);
		return ResponseEntity.ok(new UsuarioDTO(usuario));
	}

	@PutMapping("/validar")
	public ResponseEntity<UsuarioDTO> validar() {
		Usuario usuario = usuarioService.usuarioLogado();
		return ResponseEntity.ok(new UsuarioDTO(usuario));
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long id) {
		Usuario usuario = usuarioService.buscarUsuarioPorId(id);
		return ResponseEntity.ok(new UsuarioDTO(usuario));
	}

	@GetMapping
	public ResponseEntity<Page<UsuarioDTO>> buscarUsuarios(@RequestParam(required = false) Long idCargo,
			@RequestParam(required = false) String search, @RequestParam(required = false) Boolean status,
			Pageable paginacao) {
		Page<UsuarioDTO> usuarios = usuarioService.buscar(idCargo, status, search, paginacao);
		return ResponseEntity.ok(usuarios);
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<Void> ativarDesativarUsuario(@PathVariable Long id, @RequestParam Boolean status) {
		usuarioService.ativarDesativar(id, status);
		return ResponseEntity.noContent().build();
	}
}
