package com.queempanadas.controllers;

import com.queempanadas.exceptions.AbstractException;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.model.Empanada;
import com.queempanadas.model.User;
import com.queempanadas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "/api/users")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUserbyUsername(@PathVariable("username") String username) throws AbstractException {
        User user = userService.getUserbyUsername(username);
        return ResponseEntity.ok(user);
    }

}
