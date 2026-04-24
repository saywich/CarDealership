package ng.saywich.storageservice.presentation.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class CarModelsMatchersDirector {
    void direct(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
            .requestMatchers(HttpMethod.GET, "/car_models/**").permitAll()
            .requestMatchers("/car_models/**")
                .hasAnyRole("admin", "warehouse_admin");
    }
}
