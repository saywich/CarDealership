package ng.saywich.storageservice.presentation;

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
    "ng.saywich.storageservice.application",
    "ng.saywich.storageservice.infrastructure",
    "ng.saywich.storageservice.presentation",
    "ng.saywich.storageservice.domain"
})
@EnableJpaRepositories(basePackages = {"ng.saywich.storageservice.infrastructure"})
@EntityScan(basePackages = {"ng.saywich.storageservice.infrastructure"})
@EnableJpaAuditing
@EnableScheduling
public class StorageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageServiceApplication.class, args);
    }
}
