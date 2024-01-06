package com.queempanadas.controllers;

import com.queempanadas.model.User;
import com.queempanadas.model.requests.LoginRequest;
import com.queempanadas.model.response.ErrorResponse;
import com.queempanadas.model.response.LoginResponse;
import com.queempanadas.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/auth", produces = "application/json")
public class AuthController extends AbstractController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            String username = authentication.getName();
            String role = String.valueOf(authentication.getAuthorities()
                    .stream()
                    .toList()
                    .get(0));
            User user = new User(username, "", role);
            String token = jwtUtil.createToken(user);
            LoginResponse loginRes = new LoginResponse(token);

            return ResponseEntity.ok(loginRes);

        } catch (BadCredentialsException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        }
    }


}
