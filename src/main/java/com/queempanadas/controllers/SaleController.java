package com.queempanadas.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.queempanadas.exceptions.AbstractException;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.dto.NewSaleDto;
import com.queempanadas.model.Sale;
import com.queempanadas.services.SaleService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "/api/sales", produces = "application/json")
public class SaleController extends AbstractController{
    @Autowired
    SaleService saleService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String getSales(@PathParam("type") String type) throws JsonProcessingException {
        List<Sale> sales = saleService.getAllBetweenDates(type);
        return mapper.writeValueAsString(sales);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getQuality(@PathVariable("id") int id) throws FieldValidationException, JsonProcessingException {
        Sale sale = saleService.getById(id);
        return mapper.writeValueAsString(sale);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public String createSale(@RequestBody NewSaleDto saleBody) throws AbstractException, JsonProcessingException {
        Sale sale = saleService.createSale(saleBody);
        return mapper.writeValueAsString(sale);
    }


}
