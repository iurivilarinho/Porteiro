package com.br.porteiro.service;

import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@Service
public class EnviaEmailService {

	private final JavaMailSender javaMailSender;
	private final Environment environment;

	public EnviaEmailService(JavaMailSender javaMailSender, Environment environment) {
		this.javaMailSender = javaMailSender;
		this.environment = environment;
	}

	public void enviar(String para, String titulo, String conteudo, Boolean html) {
		var mensagem = javaMailSender.createMimeMessage();
		var helper = new MimeMessageHelper(mensagem, "utf-8");
		try {
			String remetente = environment.getProperty("spring.mail.username");
			mensagem.setFrom(remetente);
			helper.setTo(para);
			helper.setSubject(titulo);
			helper.setText(conteudo, html); // O segundo parâmetro (true) indica que o conteúdo é HTML
			javaMailSender.send(mensagem);
		} catch (MessagingException e) {

			e.printStackTrace();
		}
	}

	public void enviarEmailComAnexo(String para, String titulo, String conteudo, byte[] arquivo, String nomeArquivo, Boolean html)
			throws MessagingException {
		var mensagem = javaMailSender.createMimeMessage();
		var helper = new MimeMessageHelper(mensagem, html); // true indica que a mensagem tem anexos
		String remetente = environment.getProperty("spring.mail.username");
		mensagem.setFrom(remetente);
		helper.setTo(para);
		helper.setSubject(titulo);
		helper.setText(conteudo);

		InputStreamSource inputStreamSource = new ByteArrayResource(arquivo);

		helper.addAttachment(nomeArquivo, inputStreamSource);

		javaMailSender.send(mensagem);
	}
}
