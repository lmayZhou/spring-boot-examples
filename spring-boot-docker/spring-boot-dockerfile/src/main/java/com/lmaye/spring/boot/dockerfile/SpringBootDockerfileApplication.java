package com.lmaye.spring.boot.dockerfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDockerfileApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringBootDockerfileApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
