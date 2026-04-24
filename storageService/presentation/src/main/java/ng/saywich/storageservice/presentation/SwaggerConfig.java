package ng.saywich.storageservice.presentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(
        @Value("${keycloak.public-token-url}") String tokenUrl
    ) {
        return new OpenAPI()
            .addSecurityItem(new SecurityRequirement().addList("keycloak_auth"))
            .components(new Components()
                .addSecuritySchemes("keycloak_auth", new SecurityScheme()
                    .type(SecurityScheme.Type.OAUTH2)
                    .flows(new OAuthFlows()
                        .password(new OAuthFlow().tokenUrl(tokenUrl))
                    )
                )
            );
    }
}