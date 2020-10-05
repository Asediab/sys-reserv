package com.sys.establishment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient
@EnableOAuth2Client
public class EstablishmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstablishmentApplication.class, args);
    }


}
