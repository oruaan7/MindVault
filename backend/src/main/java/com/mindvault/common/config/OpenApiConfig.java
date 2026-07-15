package com.mindvault.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI mindVaultOpenAPI() {

        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()

            .info(
                new Info()
                    .title("MindVault API")
                    .description("Sistema de Segundo Cérebro Pessoal")
                    .version("v0.7.0")
                    .contact(
                        new Contact()
                            .name("Ruan Souza")
                            .email("SEU_EMAIL")
                    )
            )

            .components(
                new Components()
                    .addSecuritySchemes(
                        securitySchemeName,
                        new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                    )
            )

            .addSecurityItem(
                new SecurityRequirement()
                    .addList(securitySchemeName)
            );

    }

}
