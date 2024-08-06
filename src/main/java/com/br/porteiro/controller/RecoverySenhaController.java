package com.br.porteiro.controller;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.porteiro.authentication.RecoveryPasswordService;
import com.br.porteiro.config.SuccessDetails.SuccessResponse;

@RestController
@RequestMapping("/recuperar_senha")
public class RecoverySenhaController {

	@Autowired
	private RecoveryPasswordService recoveryPasswordService;

	private static final String RECOVERY_SUCCESS_MESSAGE = "Link de recuperação enviado para ";
	private static final String PASSWORD_CHANGED_SUCCESS_MESSAGE = "Senha alterada com sucesso! Faça login novamente para continuar.";

	@PostMapping("/solicitar/{email}")
	public ResponseEntity<SuccessResponse> soolicitarRecuperacaoSenha(@PathVariable String email) {

		recoveryPasswordService.enviaEmailRecupercao(email);

		SuccessResponse successResponse = new SuccessResponse(RECOVERY_SUCCESS_MESSAGE + email);
		return ResponseEntity.ok().body(successResponse);

	}

	@PostMapping("/recuperar/{novaSenha}/{token}")
	public ResponseEntity<SuccessResponse> recuperarSenha(@PathVariable String novaSenha, @PathVariable String token)
			throws AccessDeniedException {

		recoveryPasswordService.resetarSenha(token, novaSenha);

		SuccessResponse successResponse = new SuccessResponse(PASSWORD_CHANGED_SUCCESS_MESSAGE);
		return ResponseEntity.ok().body(successResponse);
	}
}
