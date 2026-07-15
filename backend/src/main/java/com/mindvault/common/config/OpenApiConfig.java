package com.mindvault.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI mindVaultOpenAPI() {

        return new OpenAPI()

            .info(

                new Info()

                    .title("MindVault API")

                    .description(
                        "API REST do projeto MindVault - Cofre Mental - Organização Pessoal"
                    )

                    .version("v0.9.3")

                    .contact(

                        new Contact()

                            .name("Ruan Souza")

                            .email("contatooruaan@outlook.com")

                    )

            );

    }

}
