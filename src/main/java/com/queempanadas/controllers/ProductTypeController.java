package com.queempanadas.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.model.ProductType;
import com.queempanadas.services.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "/api/product-type", produces = "application/json")
public class ProductTypeController extends AbstractController {
    @Autowired
    ProductTypeService productTypeService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ProductType>> getProductTypes() throws JsonProcessingException {
        List<ProductType> productTypes = productTypeService.getAll();
        return ResponseEntity.ok(productTypes);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ProductType> getProductType(@PathVariable("id") int id) throws FieldValidationException, JsonProcessingException {
        ProductType productType = productTypeService.getById(id);
        return ResponseEntity.ok(productType);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ProductType> deleteProductType(@PathVariable("id") int id) throws Exception {
        ProductType productType = productTypeService.deleteByKey(id);
        return ResponseEntity.ok(productType);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity<ProductType> editProductType(@RequestBody ProductType newProductType) throws Exception {
        ProductType productType = productTypeService.update(newProductType);
        return ResponseEntity.ok(productType);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ProductType> createProductType(@RequestBody ProductType newProductType) throws Exception {
        ProductType productType = productTypeService.create(newProductType);
        return ResponseEntity.ok(productType);
    }
}
