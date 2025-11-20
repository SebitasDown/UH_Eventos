package com.UH.OtherLevel.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAplConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API SpringDoc")
                        .version("1.0.0")
                        .description("Documentacion automática generada por Springdoc OpenAPI")
                        .contact(new Contact()
                                .name("Sebastian")
                                .email("mazoosebas@gmail.com")
                                .url("https://localhost:8080")
                        )
                );
    }
}
