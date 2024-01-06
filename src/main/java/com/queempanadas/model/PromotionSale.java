package com.queempanadas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity(name = "promotion_sale")

public class PromotionSale {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promotion_sale", updatable = false, nullable = false)
    private long idPromotionSale;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_sale", nullable = false)
    private Sale sale;


    @JoinColumn(name = "promotion", nullable = false)
    private String promotion;

    @Column(name = "discount")
    private int discount;

    public PromotionSale() {
    }

    public PromotionSale(Sale sale, Promo promotion) {
        this.sale = sale;
        this.promotion = promotion.getName();
        this.discount = promotion.getDiscount();
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public long getIdPromotionSale() {
        return idPromotionSale;
    }
}
