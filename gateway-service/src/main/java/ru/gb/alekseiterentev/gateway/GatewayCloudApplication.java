package ru.gb.alekseiterentev.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class GatewayCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayCloudApplication.class, args);
    }
}
