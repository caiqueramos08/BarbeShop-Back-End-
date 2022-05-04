package com.br.barberShop.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springblogPessoalOpenAPI() {
		return new OpenAPI()
				.info(new Info()
					.title("Projeto Barber Shop")
					.description("API de Barbearia construído em linguagem Java - C8 Technology")
					.version("v0.0.1")
				.license(new License()
					.name("C8 Tecnology")
					.url("https://www.instagram.com/c8.technology/"))
				.contact(new Contact()
					.name("Projeto Barber Shop - C8 Technology")
					.url("https://github.com/caiqueramos08")
					.email("caique_cerqueiraramos@hotmail.com")))
				.externalDocs(new ExternalDocumentation()
					.description("Github")
					.url("https://github.com/caiqueramos08/P/"));
	}

}
