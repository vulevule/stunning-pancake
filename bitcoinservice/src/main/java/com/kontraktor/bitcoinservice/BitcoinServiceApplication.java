package com.kontraktor.bitcoinservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author badf00d21  29.1.20.
 */

@EnableEurekaClient
@SpringBootApplication
public class BitcoinServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BitcoinServiceApplication.class, args);
    }
}
