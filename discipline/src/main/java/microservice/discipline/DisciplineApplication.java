package microservice.discipline;

import microservice.core.property.JwtConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"microservice.core.model"})
@EnableJpaRepositories({"microservice.core.repository"})
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan("microservice")
@EnableCircuitBreaker
public class DisciplineApplication {

    public static void main(String[] args) {
        SpringApplication.run(DisciplineApplication.class, args);
    }

}