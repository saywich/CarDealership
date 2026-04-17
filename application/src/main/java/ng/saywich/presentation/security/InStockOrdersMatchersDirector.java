package ng.saywich.presentation.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class InStockOrdersMatchersDirector {
    void direct(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry
            .requestMatchers(HttpMethod.GET, "/in_stock_orders/customer")
                .hasRole("user")
            .requestMatchers(HttpMethod.GET, "/in_stock_orders/manager")
                .hasRole("manager")
            .requestMatchers("/in_stock_orders/cancel")
                .hasAnyRole("user", "manager")
            .requestMatchers(HttpMethod.GET, "/in_stock_orders/all")
                .hasAnyRole("admin", "warehouse_admin")
            .requestMatchers(HttpMethod.POST, "/in_stock_orders")
                .hasRole("user")
            .requestMatchers("/in_stock_orders/**")
                .hasAnyRole("manager", "admin");
    }
}
