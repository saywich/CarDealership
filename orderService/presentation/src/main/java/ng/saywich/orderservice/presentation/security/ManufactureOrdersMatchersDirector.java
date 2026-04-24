package ng.saywich.orderservice.presentation.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class ManufactureOrdersMatchersDirector {
    void direct(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
            .requestMatchers(HttpMethod.GET, "/manufacture_orders/")
            .authenticated()
            .requestMatchers(HttpMethod.POST, "/manufacture_orders/")
            .hasAnyRole("user", "admin")
            .requestMatchers("/manufacture_orders/**")
            .hasAnyRole("manager", "admin")
            .requestMatchers("/manufacture_orders/cancel")
            .hasRole("user");
    }
}

