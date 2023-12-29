package com.queempanadas.security;

import com.queempanadas.exceptions.AuthExceptionResolver;
import com.queempanadas.exceptions.NoAuthException;
import com.queempanadas.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthExceptionResolver authHandlerExceptionResolver;
    private final UserService userService = new UserService();
    @Autowired
    private JwtGenerator jwtGenerator;

    public JwtAuthenticationFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            authHandlerExceptionResolver.resolveException(request, response, null, new NoAuthException());
            return;
        }
        try {
            final String token = authHeader.substring(7);
            if (token != null && jwtGenerator.validateToken(token)) {
                String username = jwtGenerator.getUsernameFromJWT(token);
                UserDetails userDetails = userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext()
                        .setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            authHandlerExceptionResolver.resolveException(request, response, null, exception);
        }


    }


}
