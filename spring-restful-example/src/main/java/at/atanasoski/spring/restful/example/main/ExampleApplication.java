package at.atanasoski.spring.restful.example.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("at.atanasoski.spring.restful.example") // Without this only the local package will be used
@EnableJpaRepositories("at.atanasoski.spring.restful.example")
@EntityScan("at.atanasoski.spring.restful.example")
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
