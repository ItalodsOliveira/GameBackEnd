package com.isogames.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfos(){
        return new OpenAPI().info(
                new Info()
                        .title("Documentação do projeto de games com Spring Boot")
                        .description("Documentação de API")
                        .version("1.0.0")
        );
    }

    @Bean
    public GroupedOpenApi httpApi(){
        return GroupedOpenApi.builder()
                .group("Games")
                .pathsToMatch("/game/**")
                .packagesToScan("com.isogames.app.controller")
                .build();
    }
}
