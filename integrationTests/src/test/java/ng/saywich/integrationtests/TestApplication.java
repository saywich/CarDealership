package ng.saywich.integrationtests;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "ng.saywich.storageservice.infrastructure.persistence"
})
@EnableJpaRepositories(basePackages = {
    "ng.saywich.storageservice.infrastructure.persistence.jparepositories"
})
@EntityScan(basePackages = {
    "ng.saywich.storageservice.infrastructure.persistence.models"
})
@EnableJpaAuditing
public class TestApplication {
}
