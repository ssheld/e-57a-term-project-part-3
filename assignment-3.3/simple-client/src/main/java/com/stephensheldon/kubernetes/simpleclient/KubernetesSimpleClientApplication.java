package com.stephensheldon.kubernetes.simpleclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;

/**
 * Author: Stephen Sheldon
 **/

@RestController
@SpringBootApplication
public class KubernetesSimpleClientApplication {

    private static Logger logger = LoggerFactory.getLogger(KubernetesSimpleClientApplication.class);

    @GetMapping(value = "/")
    public String get() throws UnknownHostException {

        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl = "http://simple-server:8080";

        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
        return String.format("Message from simple server is: " + response.getBody());
    }

    public static void main(String[] args) {
        SpringApplication.run(KubernetesSimpleClientApplication.class, args);
    }
}
