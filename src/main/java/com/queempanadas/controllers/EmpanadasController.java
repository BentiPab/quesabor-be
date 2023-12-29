package com.queempanadas.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.model.Empanada;
import com.queempanadas.services.EmpanadaService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/empanadas")
public class EmpanadasController extends AbstractController {
    @Autowired
    EmpanadaService empanadaService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Empanada>> getEmpanadas() {
        List<Empanada> empanadas = empanadaService.getAll();

        return ResponseEntity.ok(empanadas);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Empanada> getEmpanada(@PathVariable("id") int id) throws FieldValidationException {
        Empanada empanada = empanadaService.getById(id);
        return ResponseEntity.ok(empanada);
}

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Empanada> deleteEmpanada(@PathVariable("id") int id) throws Exception {
        Empanada empanada = empanadaService.deleteByKey(id);
        return ResponseEntity.ok(empanada);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity<Empanada> editEmpanada(@RequestBody Empanada newEmpanada) throws Exception {
        Empanada empanada = empanadaService.update(newEmpanada);
        return ResponseEntity.ok(empanada);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Empanada> createEmpanada(@RequestBody Empanada newEmpanada) throws Exception {
        Empanada empanada = empanadaService.create(newEmpanada);
        return ResponseEntity.ok(empanada);
    }

}
