package ng.saywich.orderservice.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = {
    "ng.saywich.orderservice.application",
    "ng.saywich.orderservice.infrastructure",
    "ng.saywich.orderservice.presentation",
    "ng.saywich.orderservice.domain"
})
@EnableJpaRepositories(basePackages = {"ng.saywich.orderservice.infrastructure"})
@EntityScan(basePackages = {"ng.saywich.orderservice.infrastructure"})
@EnableJpaAuditing
@EnableScheduling
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
