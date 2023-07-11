package com.raphael.itoosabi.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "iTooSabi Blog APIs",
                version = "1.0.0",
                description = "OpenApi documentation for iTooSabi",
                termsOfService = "developers choice",
                contact = @Contact(
                        name = "Raphael Ekpei",
                        email = "ekpeiraphael020@gmail.com",
                        url = "https://www.nelu.dev"
                ),
                license = @License(
                        name = "license name",
                        url = "https://www.nelu.dev"
                )
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "http://localhost:8080/course"
                )
        }
)
public class OpenApiConfiguration {


    // TODO: Swagger UI:  http://server:port/context-path/swagger-ui/index.html and
    // TODO: OpenAPI url for json format: http://server:port/context-path/v3/api-docs

}
