package com.queempanadas.controllers;

import com.queempanadas.model.Product;
import com.queempanadas.model.Promo;
import com.queempanadas.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/promotions", produces = "application/json")
public class PromotionsController {

    @Autowired
    PromotionService promotionService;
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Promo>> getPromos() {

        List<Promo> promos = promotionService.getAll();

        return ResponseEntity.ok(promos);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Promo> createProduct(@RequestBody Promo newProduct) throws Exception {
        Promo promo = promotionService.create(newProduct);
        return ResponseEntity.ok(promo);
    }
}
