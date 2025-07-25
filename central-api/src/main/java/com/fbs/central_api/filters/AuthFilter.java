package com.fbs.central_api.filters;

import com.fbs.central_api.services.UserService;
import com.fbs.central_api.utilities.AuthUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class AuthFilter extends OncePerRequestFilter {
    UserService userService;
    AuthUtility authUtility;

    @Autowired
    public AuthFilter(UserService userService,
                      AuthUtility authUtility) {
        this.userService = userService;
        this.authUtility = authUtility;
    }

    /*
        If the Token is valid then we will be setting usernamePasswordAuthentication and calling the doFilter method.
        If the Token is invalid then without setting usernamePasswordAuthentication, we are directly calling doFilter method.
     */

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            // We got the token now we need to validate that, this token is a genuine token or not
            boolean isValid = userService.validateToken(token);
            if(isValid == false) {
                // I am not going to set any kind of authentication and I will return from here itself
                // before filtering if I am not setting any kind of authentication that
                // means I am rejecting the request
                filterChain.doFilter(request, response);
                return;
            }
            String payload = authUtility.decryptJwtToken(token);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(payload, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
