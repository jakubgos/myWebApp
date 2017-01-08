package com.myapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class MyAppApplication extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(MyAppApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MyAppApplication.class);
    }


	public static void main(String[] args) {
		SpringApplication.run(MyAppApplication.class, args);

	}


}
