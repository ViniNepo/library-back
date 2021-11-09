package com.senac.library.api.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        User basicUser = new User("Basic User", "123", Arrays.asList(
                new SimpleGrantedAuthority("USER")));

        User managerUser = new User("Manager User", "123", Arrays.asList(
                new SimpleGrantedAuthority("USER")));

        return new InMemoryUserDetailsManager(Arrays.asList(
                basicUser, managerUser
        ));
    }
}