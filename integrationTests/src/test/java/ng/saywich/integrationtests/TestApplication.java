package ng.saywich.integrationtests;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "ng.saywich.application",
    "ng.saywich.infrastructure",
    "ng.saywich.presentation",
    "ng.saywich.domain"
})
@EnableJpaRepositories(basePackages = {"ng.saywich.infrastructure"})
@EntityScan(basePackages = {"ng.saywich.infrastructure"})
@EnableJpaAuditing
public class TestApplication {
}
