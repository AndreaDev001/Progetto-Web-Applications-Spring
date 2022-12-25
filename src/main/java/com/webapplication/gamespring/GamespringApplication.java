package com.webapplication.gamespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class GamespringApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamespringApplication.class, args);
    }

}
