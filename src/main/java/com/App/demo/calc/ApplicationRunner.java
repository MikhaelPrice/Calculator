package com.App.demo.calc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationRunner {

    public static void main(String[] args) throws calcException {
        SpringApplication.run(ApplicationRunner.class, args);
    }

}