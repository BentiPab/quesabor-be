package com.queempanadas.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.model.Quality;
import com.queempanadas.services.QualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "/api//qualities", produces = "application/json")
public class QualityController extends AbstractController {
    @Autowired
    QualityService qualityService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String getQualities() throws JsonProcessingException {
        List<Quality> qualities = qualityService.getAll();
        return mapper.writeValueAsString(qualities);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getQuality(@PathVariable("id") int id) throws FieldValidationException, JsonProcessingException {
        Quality quality = qualityService.getById(id);
        return mapper.writeValueAsString(quality);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteQuality(@PathVariable("id") int id) throws Exception {
        Quality quality = qualityService.deleteByKey(id);
        return mapper.writeValueAsString(quality);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public String editQuality(@RequestBody Quality newQuality) throws Exception {
        Quality quality = qualityService.update(newQuality);
        return mapper.writeValueAsString(quality);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public String createQuality(@RequestBody Quality newQuality) throws Exception {
        Quality quality = qualityService.create(newQuality);
        return mapper.writeValueAsString(quality);
    }
}
