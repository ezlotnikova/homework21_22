package com.gmail.ezlotnikova.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.gmail.ezlotnikova.springboot",
        "com.gmail.ezlotnikova.service",
        "com.gmail.ezlotnikova.repository"},
        exclude = UserDetailsServiceAutoConfiguration.class)
public class SpringbootModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootModuleApplication.class, args);
    }

}
