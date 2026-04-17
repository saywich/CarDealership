package ng.saywich.presentation.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class CarBrandsMatchersDirector {
    void direct(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
            .requestMatchers(HttpMethod.GET, "/car_brands/**").permitAll()
            .requestMatchers("/car_brands/**")
                .hasAnyRole("admin", "warehouse_admin");
    }
}

