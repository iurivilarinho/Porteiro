package com.br.porteiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.porteiro.authentication.LoginForm;
import com.br.porteiro.authentication.TokenService;
import com.br.porteiro.models.Usuario;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<Usuario> efetuarLogin(@RequestBody LoginForm dados, HttpServletRequest request) {

		try {
			var authenticationToken = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());
			var authentication = manager.authenticate(authenticationToken);
			var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
			Usuario usuario = tokenService.getUsuarioPorToken(tokenJWT);

			HttpHeaders headers = new HttpHeaders();

			headers.add("Authorization", "Bearer " + tokenJWT);

			// Adiciona o token no cookie com SameSite=None e Secure
			ResponseCookie cookie = ResponseCookie.from("token", tokenJWT).httpOnly(true).secure(true).sameSite("None")
					.maxAge(604800).path("/").build();

			// Adiciona o cookie no cabeçalho da resposta
			headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

			return ResponseEntity.ok().headers(headers).body(usuario);
		} catch (BadCredentialsException e) {
			throw new AccessDeniedException("Senha incorreta! Tente novamente.");
		}
	}
}
