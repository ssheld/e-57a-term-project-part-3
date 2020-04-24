package com.stephensheldon.authentication.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Author: Stephen Sheldon
 **/

public class UserContextInterceptor implements ClientHttpRequestInterceptor {

    //The intercept() method is invoked before the actual HTTP service call occurs by the RestTemplate.
    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        HttpHeaders headers = request.getHeaders();

        // Take the HTTP request header that’s being prepared for the outgoing service call
        // and add the correlation ID stored in the UserContext.
        headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());

        // If you examine security, then auth token can be added in the same way.
        headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());

        return execution.execute(request, body);
    }
}
