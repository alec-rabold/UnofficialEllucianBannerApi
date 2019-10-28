package io.collegeplanner.my.EllucianDataApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EllucianDataScraperApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(EllucianDataScraperApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(EllucianDataScraperApplication.class, args);
    }
}

