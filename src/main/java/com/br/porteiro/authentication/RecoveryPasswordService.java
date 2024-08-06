package com.br.porteiro.authentication;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.porteiro.service.EnviaEmailService;
import com.br.porteiro.service.UsuarioService;

@Service
public class RecoveryPasswordService {

	@Autowired
	private EnviaEmailService enviaEmailService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioService usuarioService;

//	@Autowired
//	private ParametrosGlobaisRepository parametrosGlobaisRepository;

	public void enviaEmailRecupercao(String email) {

		// Documento img =
		// parametrosGlobaisRepository.findById(102l).orElseThrow().getImagemEmailRecuperacaoSenha();

		// String base64Image = Base64.getEncoder().encodeToString(img.getDocumento());

		String token = tokenService.gerarTokenRecupercaoSenha(usuarioService.buscarUsuarioPorEmail(email));

		String emailContent = String.format("<html><body><h2>Sistema de Projetos</h2>"
				+ "<p>Clique <a href=\"http://192.168.228.8:9090/recuperar_senha/%s\">aqui</a> para alterar sua senha. Se você não solicitou a recuperação, por favor, ignore este e-mail."
				+ "</p></body></html>", token);

		enviaEmailService.enviar(email, "Recuperação de Senha - Brasil Inspeção", emailContent, true);

	}

	public void resetarSenha(String token, String novaSenha) throws AccessDeniedException {

		if (tokenService.isTokenValido(token)) {
			usuarioService.resetaSenha(usuarioService.getUsuarioPorToken(token), novaSenha);

		} else {
			throw new AccessDeniedException("Token de recuperação de senha invalido!");
		}

	}

}
