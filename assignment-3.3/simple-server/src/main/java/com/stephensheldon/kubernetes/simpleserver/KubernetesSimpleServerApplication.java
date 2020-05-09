package com.stephensheldon.kubernetes.simpleserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Author: Stephen Sheldon
 **/

@RestController
@SpringBootApplication
public class KubernetesSimpleServerApplication {

    @GetMapping(value = "/")
    public String get() throws UnknownHostException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The simple server hostname is ")
                .append(InetAddress
                        .getLocalHost()
                        .getHostName())
                .append(InetAddress
                        .getLocalHost()
                        .getHostAddress());
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(KubernetesSimpleServerApplication.class, args);
    }
}
