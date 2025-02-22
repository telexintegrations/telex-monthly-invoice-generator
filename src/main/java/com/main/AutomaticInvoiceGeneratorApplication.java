package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutomaticInvoiceGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomaticInvoiceGeneratorApplication.class, args);
    }

}
