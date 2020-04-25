package com.stephensheldon.organization;

import com.stephensheldon.organization.utils.UserContextFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.servlet.Filter;

/**
 * Author: Stephen Sheldon
 **/

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableResourceServer
public class Application {

    @Bean
    public Filter userContextFilter() {
        return new UserContextFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
