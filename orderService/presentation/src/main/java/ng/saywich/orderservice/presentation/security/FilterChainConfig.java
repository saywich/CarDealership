package ng.saywich.orderservice.presentation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class FilterChainConfig {
    @Autowired
    SwaggerMatchersDirector swaggerMatchersDirector;

    @Autowired
    InStockOrdersMatchersDirector inStockOrdersMatchersDirector;

    @Autowired
    ManufactureOrdersMatchersDirector manufactureOrdersMatchersDirector;

    @Autowired
    TestDriveOrdersMatchersDirector testDriveOrdersMatchersDirector;

    @Autowired
    JwtAuthenticationConverter jwtAuthenticationConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
            .csrf(CsrfConfigurer::disable)
            .authorizeHttpRequests(request -> {
                    swaggerMatchersDirector.direct(request);
                    inStockOrdersMatchersDirector.direct(request);
                    manufactureOrdersMatchersDirector.direct(request);
                    testDriveOrdersMatchersDirector.direct(request);

                    request.anyRequest().authenticated();
                }
            )
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(
                jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)
            ));

        return http.build();
    }
}
