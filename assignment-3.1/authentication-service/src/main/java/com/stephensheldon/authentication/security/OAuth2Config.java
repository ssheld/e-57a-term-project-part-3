package com.stephensheldon.authentication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * Author: Stephen Sheldon
 **/

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    // Defines which clients are able to be registered with our OAuth2 service.
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // Use in-memory store for client configuration
        clients.inMemory()
                .withClient("eagleeye") // Creates "eagleeye" instance of ClientDetailsServiceBuilder.ClientBuilder
                .secret("thisissecret") // Set secret for our ClientBuilder "eagleeye"
                .authorizedGrantTypes("refresh_token", "password", "client_credentials") // Set authorized grant types on our ClientBuilder
                .scopes("webclient", "mobileclient"); // Set scope to web client and mobile client
    }

    // Configure the non-security features of the Authorization Server endpoints, like token store, token customizations,
    // user approvals and grant types.
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager) //use injected authenticationManager to process Auth requests.
                .userDetailsService(userDetailsService); //set userDetailsService
    }
}
