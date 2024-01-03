package com.queempanadas.controllers;

import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.model.Product;
import com.queempanadas.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/products")
public class ProductController extends AbstractController {
    @Autowired
    ProductService productService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Product>> getProducts() {

        List<Product> products = productService.getAll();

        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id) throws FieldValidationException {
        Product product = productService.getById(id);
        return ResponseEntity.ok(product);
}

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id) throws Exception {
        Product product = productService.deleteByKey(id);
        return ResponseEntity.ok(product);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity<Product> editProduct(@RequestBody Product newProduct) throws Exception {
        Product product = productService.update(newProduct);
        return ResponseEntity.ok(product);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct) throws Exception {
        Product product = productService.create(newProduct);
        return ResponseEntity.ok(product);
    }

}
