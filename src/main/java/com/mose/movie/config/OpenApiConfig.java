package com.mose.movie.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Value("${app.version}")
    private String version;

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("integrated api doc")
                .version(version);

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}