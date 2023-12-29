package com.queempanadas.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.queempanadas.dto.ErrorDto;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SignatureException;

@Component
public class AuthExceptionResolver implements HandlerExceptionResolver {

    private ObjectMapper mapper = new ObjectMapper();
    private PrintWriter out = null;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof BadCredentialsException) {
            ModelAndView model = new ModelAndView();
            ErrorDto errorDto = new ErrorDto(HttpStatus.UNAUTHORIZED.value(), "Invalid credentials");
            response.setStatus(errorDto.getStatus());
            response.setContentType("application/json");
            try {
                this.out = response.getWriter();
                this.out.print(mapper.writeValueAsString(errorDto));
                this.out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return model;
        }

        if (ex instanceof AccountStatusException) {
            ModelAndView model = new ModelAndView();
            ErrorDto errorDto = new ErrorDto(HttpStatus.FORBIDDEN.value(), "Account locked");
            response.setStatus(errorDto.getStatus());
            response.setContentType("application/json");
            try {
                this.out = response.getWriter();
                this.out.print(mapper.writeValueAsString(errorDto));
                this.out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return model;
        }

        if (ex instanceof NoAuthException) {
            ModelAndView model = new ModelAndView();
            ErrorDto errorDto = new ErrorDto(HttpStatus.UNAUTHORIZED.value(), "No credentials provided");
            response.setStatus(errorDto.getStatus());
            response.setContentType("application/json");
            try {
                this.out = response.getWriter();
                this.out.print(mapper.writeValueAsString(errorDto));
                this.out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return model;
        }

        if (ex instanceof AccessDeniedException) {
            ModelAndView model = new ModelAndView();
            ErrorDto errorDto = new ErrorDto(HttpStatus.FORBIDDEN.value(), "Access denied");
            response.setStatus(errorDto.getStatus());
            response.setContentType("application/json");
            try {
                this.out = response.getWriter();
                this.out.print(mapper.writeValueAsString(errorDto));
                this.out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return model;
        }

        if (ex instanceof SignatureException || ex instanceof AuthenticationCredentialsNotFoundException) {
            ModelAndView model = new ModelAndView();
            ErrorDto errorDto = new ErrorDto(HttpStatus.UNAUTHORIZED.value(), "Invalid Token");
            response.setStatus(errorDto.getStatus());
            response.setContentType("application/json");
            try {
                this.out = response.getWriter();
                this.out.print(mapper.writeValueAsString(errorDto));
                this.out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return model;
        }

        if (ex instanceof ExpiredJwtException) {
            ModelAndView model = new ModelAndView();
            ErrorDto errorDto = new ErrorDto(HttpStatus.UNAUTHORIZED.value(), "Expired Token");
            response.setStatus(errorDto.getStatus());
            response.setContentType("application/json");
            try {
                this.out = response.getWriter();
                this.out.print(mapper.writeValueAsString(errorDto));
                this.out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return model;
        }


        ModelAndView model = new ModelAndView();
        model.setView(new MappingJackson2JsonView());
        model.addObject("exception", "Unknown internal server error.");
        return model;
    }
}
