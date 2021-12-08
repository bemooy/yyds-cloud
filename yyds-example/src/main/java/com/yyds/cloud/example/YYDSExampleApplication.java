package com.yyds.cloud.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.yyds.cloud"})
public class YYDSExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(YYDSExampleApplication.class, args);
    }

}