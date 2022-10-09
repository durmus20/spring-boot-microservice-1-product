package com.sha.springbootmicroservice1product.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class SecurityConfig {

        @Value("${service.security.secure-key-username}")
        private String SECURE_KEY_USERNAME;

        @Value("${service.security.secure-key-password}")
        private String SECURE_KEY_PASSWORD;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.formLogin(form -> {
                form.loginPage("/login")
                        .permitAll();
            }).authorizeRequests();
            return http.build();
        }


        @Bean
        @Description("In memory Userdetails service registered since DB doesn't have user table ")
        public UserDetailsService users() {
            // The builder will ensure the passwords are encoded before saving in memory
            UserDetails user = User.builder()
                    .username(SECURE_KEY_USERNAME)
                    .password(SECURE_KEY_PASSWORD)
                    .roles("USER")
                    .build();
            UserDetails admin = User.builder()
                    .username(SECURE_KEY_USERNAME)
                    .password(SECURE_KEY_PASSWORD)
                    .roles("USER", "ADMIN")
                    .build();
            return new InMemoryUserDetailsManager(user, admin);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

    }