package com.felix.wbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by fsoewito on 2/23/2016.
 */

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
