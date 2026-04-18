package ng.saywich.presentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .addSecurityItem(new SecurityRequirement().addList("keycloak_auth"))
            .components(new Components()
                .addSecuritySchemes("keycloak_auth", new SecurityScheme()
                    .type(SecurityScheme.Type.OAUTH2)
                    .flows(new OAuthFlows()
                        .password(new OAuthFlow()
                            .tokenUrl("http://localhost:9090/realms/automotive-auth/protocol/openid-connect/token")
                        )
                    )
                )
            );
    }
}