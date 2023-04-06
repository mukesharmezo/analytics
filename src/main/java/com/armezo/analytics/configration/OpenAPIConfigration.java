package com.armezo.analytics.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIConfigration {

	@Bean
	public OpenAPI openApiBean() {
		final String securityName = "bearerAuth";
		return new OpenAPI()
				.components(new Components())
				.info(new Info()
						.title("Analytics Application API")
						.description("Get & Update Dashboard Data From Here")
						.termsOfService("https://www.armezosolutions.com/privacy-policy.jsp")
						.contact(new Contact().email("contact@armezosolutions.com"))
						.license(new License().url("https://www.armezosolutions.com/privacy-policy.jsp")) //name("Armezo Solutions"))
						.version("1.0")
						)
				.addSecurityItem(new SecurityRequirement()
						.addList(securityName))
				.components(new Components()
						.addSecuritySchemes(securityName, new SecurityScheme()
								.name(securityName)
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT")));
	}
	// Security Config
/*	@Bean
	public OpenAPI securityConfigCustom() {
		final String securityName = "bearerAuth";
		return new OpenAPI()
				.addSecurityItem(new SecurityRequirement()
						.addList(securityName))
				.components(new Components()
						.addSecuritySchemes(securityName, new SecurityScheme()
								.name(securityName)
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT")));
	}*/
}
