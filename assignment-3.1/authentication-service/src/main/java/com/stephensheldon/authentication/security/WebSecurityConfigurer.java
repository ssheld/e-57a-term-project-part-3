package com.stephensheldon.authentication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Author: Stephen Sheldon
 **/

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    // The Authentication- ManagerBean is used by Spring Security to handle authentication.
    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    // The UserDetailsService is used by Spring Security to handle user information that
    // will be returned the Spring Security.
    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    // This method defines users, their passwords, and their roles.
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("jesus.quintana").password("password1").roles("USER")
                .and()
                .withUser("walter.sobchak").password("password2").roles("USER", "ADMIN");
    }
}
