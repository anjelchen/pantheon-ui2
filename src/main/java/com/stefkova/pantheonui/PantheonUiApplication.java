package com.stefkova.pantheonui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PantheonUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PantheonUiApplication.class, args);
    }

}
