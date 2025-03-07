package com.dudko.example;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
        info = @Info(
                title = "RAG Service with Spring AI",
                description = "Example of RAG Service with Spring AI",
                version = "v0.0.1",
                contact = @Contact(
                        name = "Dudko Anatol",
                        email = "anatoly_dudko@icloud.com"
                )
        )
)
@SpringBootApplication
public class RagService {

    public static void main(String[] args) {
        SpringApplication.run(RagService.class, args);
    }

}
