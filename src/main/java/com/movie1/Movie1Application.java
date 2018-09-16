package com.movie1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EnableJpaRepositories("com.movie1.repository")
public class Movie1Application {

    public static void main(String[] args) {
        SpringApplication.run(Movie1Application.class, args);
    }
}
