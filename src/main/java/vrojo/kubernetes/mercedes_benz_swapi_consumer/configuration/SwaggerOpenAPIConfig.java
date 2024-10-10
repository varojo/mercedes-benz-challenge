package vrojo.kubernetes.mercedes_benz_swapi_consumer.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author valentin.rojo
 */
@Configuration
public class SwaggerOpenAPIConfig {
    private static final String SCHEME_NAME = "basicAuth";
    private static final String SCHEME = "basic";

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name(SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme(SCHEME);
    }

    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setEmail("vrojobianchi@gmail.com");
        contact.setName("Lead DevOps Engineer");
        contact.url("www.linkedin.com/in/valentín-rojo-bianchi-ab192218a");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("SWAPI Consumer MW API - Mercedes Benz")
                .version("1.0.0")
                .contact(contact)
                .description("This middleware consumes the Star Wars API and transforms the results.")
                .license(mitLicense);

        OpenAPI openAPI = new OpenAPI();
        openAPI.info(info);
        openAPI.components(new Components()
                .addSecuritySchemes(SCHEME_NAME, createSecurityScheme()))
                .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME));

        return openAPI;
    }
}
