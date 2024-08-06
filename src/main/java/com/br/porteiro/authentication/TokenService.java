package com.br.porteiro.authentication;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.porteiro.models.Perfil;
import com.br.porteiro.models.Usuario;
import com.br.porteiro.repository.UsuarioRepository;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;

	@Value("${api.jwt.expiration}")
	private String expiration;

	@Value("${api.jwt.expiration.recovery.password}")
	private String expirationRecoveryPassowrd;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public String gerarToken(Usuario usuario) {

		try {
			List<String> perfis = usuario.getPerfis().stream().map(Perfil::getNome).collect(Collectors.toList());
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.create().withIssuer("API Autenticacao").withSubject(usuario.getId().toString())
					.withClaim("perfis", perfis).withExpiresAt(dataExpiracao()).sign(algoritmo);
		} catch (JWTCreationException exception) {

			throw new RuntimeException("erro ao gerar token jwt", exception);
		}
	}

	public String gerarTokenRecupercaoSenha(Usuario usuario) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.create().withIssuer("API Autenticacao").withSubject(usuario.getId().toString())
					.withExpiresAt(dataExpiracaoRecupercaoSenha()).sign(algoritmo);
		} catch (JWTCreationException exception) {

			throw new RuntimeException("erro ao gerar token jwt", exception);
		}
	}

	public String getSubject(String tokenJWT) {

		var algoritmo = Algorithm.HMAC256(secret);
		return JWT.require(algoritmo).withIssuer("API Autenticacao").build().verify(tokenJWT).getSubject();

	}

	public boolean isTokenValido(String tokenJWT) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algoritmo).withIssuer("API Autenticacao").build();
			verifier.verify(tokenJWT);
			return true;
		} catch (JWTVerificationException e) {

			return false;
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusDays(Integer.parseInt(expiration)).toInstant(ZoneOffset.of("-03:00"));
	}

	private Instant dataExpiracaoRecupercaoSenha() {
		return LocalDateTime.now().plusHours(Integer.parseInt(expiration)).toInstant(ZoneOffset.of("-03:00"));
	}

	public Usuario getUsuarioPorToken(String tokenJWT) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("API Autenticacao").build();
			DecodedJWT decodedJWT = verifier.verify(tokenJWT);

			String id = decodedJWT.getSubject(); // Assume que o subject contém o login do usuário

			Usuario usuario = usuarioRepository.findById(Long.parseLong(id)).orElseThrow();

			return usuario;

		} catch (JWTDecodeException e) {

			throw new DataIntegrityViolationException("token invalido!");
		}

	}

}
