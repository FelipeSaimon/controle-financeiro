package com.saimon.controle_financeiro.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerDoc {

	private Contact contact() {
		return new Contact()
				.name("Nome")
				.url("https://www.meusite.com")
				.email("contact@meusite.com");
	}

	@Bean
	public OpenAPI customApi() {
		return new OpenAPI()
				.info(new Info()
						.title("Rest API - Gestão financeira simplificada")
						.description("Documentacao do projeto de gestão financeira")
						.version("1.0")
						.termsOfService("Termo de uso - Open Source")
						.license(new License()
								.name("Licenca - Devciente")
								.url("https://www.meusite.com")
						)
						.contact(contact())
				)
				.servers(List.of(new Server().url("/")));
	}
}
