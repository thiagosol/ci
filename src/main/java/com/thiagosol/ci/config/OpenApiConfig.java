package com.thiagosol.ci.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI creditHubOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("So Pick-Up Soccer Game - REST API")
                        .description("Thiago Sol API for So Pick-Up Soccer Game")
                        .version("1.0.0")
                        .license(new License().name("Apache License Version 2.").url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
