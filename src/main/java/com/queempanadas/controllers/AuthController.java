package com.queempanadas.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.queempanadas.dto.AuthBody;
import com.queempanadas.dto.AuthLoginDto;
import com.queempanadas.security.JwtGenerator;
import com.queempanadas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/auth", produces = "application/json")
public class AuthController extends AbstractController{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtGenerator jwtGenerator;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody AuthBody adminAuthDto) throws JsonProcessingException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(adminAuthDto.getUsername(), adminAuthDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);
        AuthLoginDto responseDto = new AuthLoginDto();
        responseDto.setSuccess(true);
        responseDto.setMessage("login successful !!");
        responseDto.setToken(token);
        return mapper.writeValueAsString(responseDto);
    }

    @RequestMapping(value = "/logut", method = RequestMethod.POST)
    public String loguot(@RequestBody AuthBody adminAuthDto) throws JsonProcessingException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(adminAuthDto.getUsername(), adminAuthDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);
        AuthLoginDto responseDto = new AuthLoginDto();
        responseDto.setSuccess(true);
        responseDto.setMessage("login successful !!");
        responseDto.setToken(token);
        UserDetails admin = userService.loadUserByUsername(adminAuthDto.getUsername());
        return mapper.writeValueAsString(responseDto);
    }
}
