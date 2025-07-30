package com.fbs.central_api.configurations;

import com.fbs.central_api.filters.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class AuthConfiguration {
    AuthFilter authFilter;

    @Autowired
    public AuthConfiguration(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    /**
     * In this SecurityFilterChain we are declaring what all endpoints will not be secured.
     * Other than that all the endpoints will be secured.
     * @param http
     * @return
     * @throws Exception
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/central/user/login",
                                "/api/v1/central/airline/register",
                                "api/v1/central/airline/request/accept/{airlineId}",
                                "api/v1/central/user/search"
                        ).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
