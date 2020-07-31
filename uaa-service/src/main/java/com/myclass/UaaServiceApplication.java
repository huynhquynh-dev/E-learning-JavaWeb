package com.myclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableResourceServer
public class UaaServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(UaaServiceApplication.class, args);
    }

    @Autowired
    private PasswordEncoder userPasswordEncoder;

    @Autowired
    private PasswordEncoder oauthClientPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String password = "password";
        System.out.println("userPasswordEncoder: " + userPasswordEncoder.encode(password));
        System.out.println("oauthClientPasswordEncoder: " + oauthClientPasswordEncoder.encode(password));
    }
}
