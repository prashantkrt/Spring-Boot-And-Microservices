package com.myLearning.beanValidation.exceptionHandling.documentation;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Course Rest API Documentation",
                description = "Course microservice RestAPI Documentation details",
                version = "v1",
                termsOfService = "https://prashantkrt.github.io/",
                contact = @Contact(
                        name = "Prashant",
                        url = "https://prashantkrt.github.io/", // Prashant website link
                        email = "abc@abc.com" // prashant email
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://prashantkrt.github.io/",
                        identifier = "Apache"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Course RestAPI Documentation details",
                url = "https://prashantkrt.github.io/"
        ),
        servers = {
                @Server(url = "http://localhost:8084/", description = "Base server url"),
                @Server(url = "http://localhost:8084/swagger-ui.html", description = "Swagger UI"),
                @Server(url = "http://localhost:8084/v3/api-docs", description = "App documents")
        }
)
public class CourseServiceProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseServiceProducerApplication.class, args);
    }
}
