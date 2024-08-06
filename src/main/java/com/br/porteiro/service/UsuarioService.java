package com.br.porteiro.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.porteiro.dto.UsuarioDTO;
import com.br.porteiro.filtro.UsuarioFiltro;
import com.br.porteiro.form.UsuarioForm;
import com.br.porteiro.models.Usuario;
import com.br.porteiro.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private DocumentoService documentoService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioFiltro filtro;

	@Value("${api.security.token.secret}")
	private String secret;

	@Transactional(readOnly = true)
	public Usuario buscarUsuarioPorEmail(String email) {
		return usuarioRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(
				"Email não encontrado na nossa base de dados! Verifique e tente novamente."));
	}

	@Transactional(readOnly = true)
	public Usuario usuarioLogado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			String username = authentication.getName();
			return usuarioRepository.findByLogin(username).orElse(null);
		}
		return null;
	}

	@Transactional
	public void resetaSenha(Usuario usuario, String novaSenha) {
		usuario.setSenha(novaSenha);
		usuarioRepository.save(usuario);
	}

	@Transactional(readOnly = true)
	public Usuario getUsuarioPorToken(String tokenJWT) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("API Voll.med").build();
			DecodedJWT decodedJWT = verifier.verify(tokenJWT);
			String login = decodedJWT.getSubject(); // Assume que o subject contém o login do usuário
			Usuario usuario = usuarioRepository.findByLogin(login).orElseThrow(
					() -> new EntityNotFoundException("Usuário não encontrado para o login informado! " + login));
			return usuario;
		} catch (JWTDecodeException e) {
			throw new DataIntegrityViolationException("token invalido!");
		}

	}

	@Transactional(readOnly = true)
	public void validaEmail(String email) {
		usuarioRepository.findByEmail(email).ifPresent((p) -> {
			throw new DataIntegrityViolationException("O e-mail " + p.getEmail() + " já esta cadastrado.");
		});
	}

	@Transactional(readOnly = true)
	public void validaLogin(String login) {
		usuarioRepository.findByLogin(login).ifPresent((p) -> {
			throw new DataIntegrityViolationException("O login " + p.getLogin() + " já esta cadastrado.");
		});
	}

	@Transactional(readOnly = true)
	public Usuario buscaUsuarioPorLogin(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado para o ID " + id));
	}

	@Transactional
	public Usuario cadastrar(UsuarioForm form, MultipartFile img) throws IOException {
		validaEmail(form.getEmail());
		validaLogin(form.getLogin());
		Usuario usuario = new Usuario(form, img != null ? documentoService.converterEmDocumento(img) : null);
		usuarioRepository.save(usuario);
		return usuario;
	}

	@Transactional
	public Usuario atualizar(Long idUsuario, UsuarioForm form, MultipartFile img) throws IOException {
		Usuario usuario = buscarUsuarioPorId(idUsuario);
		usuario.setLogin(form.getLogin());
		usuario.setNome(form.getNome());
		usuario.setSenha(form.getSenha());
		usuario.setCpf(form.getCpf());
		usuario.setCelularCorporativo(form.getCelularCorporativo());
		usuario.setCelularPessoal(form.getCelularPessoal());
		usuario.setEmail(form.getEmail());

//		if (form.getPerfis() != null) {
//			usuario.getPerfis().clear();
//			usuario.getPerfis().addAll(form.getPerfis());
//		}

		usuario.setCargo(
				form.getCargo() != null ? form.getCargo() : usuario.getCargo() != null ? usuario.getCargo() : null);

		usuario.setImagem(img != null ? documentoService.converterEmDocumento(img)
				: usuario.getImagem() != null ? usuario.getImagem() : null);

		usuarioRepository.save(usuario);
		return usuario;
	}

	@Transactional(readOnly = true)
	public Usuario buscarUsuarioPorId(Long idUsuario) {
		return usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado para o ID: " + idUsuario));
	}

	@Transactional(readOnly = true)
	public Page<UsuarioDTO> buscar(Long idCargo, Boolean status, String search, Pageable paginacao) {
		return filtro.filtro(idCargo, status, search, paginacao).map(UsuarioDTO::new);
	}

	@Transactional
	public void ativarDesativar(Long idUsuario, Boolean status) {
		Usuario usuario = buscarUsuarioPorId(idUsuario);
		usuario.setStatus(status);
		usuarioRepository.save(usuario);
	}

}
