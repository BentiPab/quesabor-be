package com.queempanadas.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.queempanadas.exceptions.AbstractException;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.model.Entry;
import com.queempanadas.dto.NewEntryDto;
import com.queempanadas.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "/api/entries", produces = "application/json")
public class EntryController extends AbstractController{
    @Autowired
    EntryService entryService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Entry>> getSales() throws JsonProcessingException {
        List<Entry> entries = entryService.getAll();
        return ResponseEntity.ok(entries);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Entry> getQuality(@PathVariable("id") int id) throws FieldValidationException, JsonProcessingException {
        Entry entry = entryService.getById(id);
        return ResponseEntity.ok(entry);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Entry> createEntry(@RequestBody NewEntryDto entryBody) throws AbstractException, JsonProcessingException {
        Entry entry = entryService.createEntry(entryBody);
        return ResponseEntity.ok(entry);
    }


}
