package com.apiips.ips.swagger;

import io.swagger.annotations.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.*;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("IPS APISAS")
                        .description("En esta API tenemos la funcionalidad que soporta la operación de la IPS APISAS")
                        .version("1.0.0"));
    }
}

