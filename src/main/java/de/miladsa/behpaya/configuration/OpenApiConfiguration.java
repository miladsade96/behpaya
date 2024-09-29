package de.miladsa.behpaya.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact contact = new Contact();
        contact.setName("Milad Sadeghi DM");
        contact.setEmail("MiladSadeghiDM@Gmail.com");

        Info info = new Info();
        info.title("Java Backend Development Evaluation Project");
        info.version("0.0.1");
        info.contact(contact);
        return new OpenAPI().info(info).servers(List.of(server));
    }
}
