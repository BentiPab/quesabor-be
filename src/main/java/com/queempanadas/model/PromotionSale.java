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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_promo", nullable = false)
    private Promo promotion;

    @Column(name = "discount")
    private int discount;

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Promo getPromotion() {
        return promotion;
    }

    public void setPromotion(Promo promotion) {
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
