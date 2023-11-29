package com.inditex.storage.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(servers = {
        @Server(url = "https://dev.inditex.ch/api", description = "Dev Server URL"),
        @Server(url = "https://ref.inditex.ch/api", description = "Ref Server URL"),
        @Server(url = "https://test.inditex.ch/api", description = "Test Server URL"),
        @Server(url = "https://inditex.ch/api", description = "Production Server URL")},
        info = @Info(title = "Store Manager Service API",
                contact = @Contact(url = "https://inditex.ch",
                        name = "Inditex",
                        email = "contact@inditex.com"),
                description = "This API exposes endpoints to manage all data for the store",
                version = "1.0",
                license = @License(name = "MIT License", url = "https://choosealicense.com/licenses/mit/")
        ))
public class OpenApiConfig {
}