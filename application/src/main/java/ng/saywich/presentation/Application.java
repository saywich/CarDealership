package ng.saywich.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = {
    "ng.saywich.application",
    "ng.saywich.infrastructure",
    "ng.saywich.presentation",
    "ng.saywich.domain"
})
@EnableJpaRepositories(basePackages = {"ng.saywich.infrastructure"})
@EntityScan(basePackages = {"ng.saywich.infrastructure"})
@EnableJpaAuditing
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}