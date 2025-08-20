package br.com.catalogo.produtos.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

	@Bean
	public GroupedOpenApi grupoPublicacoes() {
		return GroupedOpenApi.builder()
				.group("Catalogo")
				.pathsToMatch("/api/**")
				.build();
	}

    @Bean
    public OpenAPI api(
            @Value("${app.name}") String name,
            @Value("${app.description}") String description,
            @Value("${app.version}") String version
    ) {
        return new OpenAPI()
                .info(new Info()
                        .title(name)
                        .description(description)
                        .version(version)
                        .contact(contact())
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                )
                .servers(List.of(new Server().url("http://localhost:8088").description("Local")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação OpenAPI")
                        .url("/v3/api-docs"));
    }

	private Contact contact() {
		Contact contact = new Contact();
		contact.setEmail("fabio.andreaza@icloud.com");
		contact.setName("Fabio Andreaza");
		return contact;
	}

}
