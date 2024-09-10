package com.br.porteiro.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.br.porteiro.service.UsuarioService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if ((request.getRequestURI().equals("/login") && request.getMethod().equals("POST"))
				|| request.getRequestURI().startsWith("/rifas") || request.getRequestURI().startsWith("/pessoas")
				|| request.getRequestURI().startsWith("/swagger") || request.getRequestURI().startsWith("/v3")
				|| request.getRequestURI().startsWith("/reservation")
				|| request.getRequestURI().startsWith("/recuperar_senha")) {
			filterChain.doFilter(request, response);
			return;
		}

		var tokenJWT = recuperarToken(request, response);

		if (tokenJWT != null && tokenService.isTokenValido(tokenJWT)) {
			var subject = tokenService.getSubject(tokenJWT);
			System.out.println("toke: " + tokenJWT);
			var usuario = usuarioService.buscarUsuarioPorId(Long.parseLong(subject));

			if (usuario != null) {
				var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				handleUsuarioNaoEncontrado(response);
				return;
			}
		} else {
			handleTokenInvalido(response);
			return;
		}

		filterChain.doFilter(request, response);
	}

	private void handleUsuarioNaoEncontrado(HttpServletResponse response) throws IOException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json;charset=UTF-8");

		try (PrintWriter writer = response.getWriter()) {
			writer.write("{ \"timestamp\": \"" + LocalDateTime.now() + "\", ");
			writer.write("\"message\": [\"Usuário não encontrado.\"] }");
			writer.flush();
		} catch (IOException e) {
			// Lidar com a exceção, se necessário
		}
	}

	private void handleTokenInvalido(HttpServletResponse response) throws IOException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json;charset=UTF-8");

		try (PrintWriter writer = response.getWriter()) {
			writer.write("{ \"timestamp\": \"" + LocalDateTime.now() + "\", ");
			writer.write("\"message\": [\"Token inválido ou expirado! Entre novamente.\"] }");
			writer.flush();
		} catch (IOException e) {
			// Lidar com a exceção, se necessário
			e.printStackTrace(); // Imprima a exceção ou trate de acordo com suas necessidades
			System.out.println("aqui");
		}
	}

	private String recuperarToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("token".equals(cookie.getName()))
					System.out.println("nome token " + cookie.getName());
				{
					return cookie.getValue();
				}
			}
		}

		String tokenHeader = request.getHeader("Authorization");
		if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			return tokenHeader.substring(7); // Remove "Bearer " para obter apenas o token
		}

		handleTokenInvalido(response);
		return null;
	}

}
