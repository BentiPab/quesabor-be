package com.queempanadas.controllers;

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
    public ResponseEntity<Promo> createPromo(@RequestBody Promo newPromo) throws Exception {
        Promo promo = promotionService.create(newPromo);
        return ResponseEntity.ok(promo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity<Promo> editPromo(@RequestBody Promo promoToEdit) throws Exception {
        Promo promo = promotionService.create(promoToEdit);
        return ResponseEntity.ok(promo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Promo> deletePromo(@PathVariable("id") int id) throws Exception {
        Promo promo = promotionService.deleteByKey(id);
        return ResponseEntity.ok(promo);
    }
}
