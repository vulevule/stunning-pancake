package com.sep2019.cryptopaymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CryptoPaymentService extends SpringBootServletInitializer

{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CryptoPaymentService.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CryptoPaymentService.class, args);
    }
}
