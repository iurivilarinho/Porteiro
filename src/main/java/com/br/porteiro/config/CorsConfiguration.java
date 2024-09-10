package com.br.porteiro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:5173", "http://192.168.228.170:5173", "http://192.168.228.126:5173",
						"http://192.168.137.1:5173", "http://192.168.1.3:5173/")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
				.allowCredentials(true) // Permitir credenciais (cookies)
				.allowedHeaders("*"); // Permitir todos os cabeçalhos
		;
	}
}