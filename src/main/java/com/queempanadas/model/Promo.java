package com.queempanadas.model;

import jakarta.persistence.*;

@Entity(name = "promotions")
@NamedQueries(value = {
        @NamedQuery(name = "GetAllPromos", query = "from promotions"),
        @NamedQuery(name = "GetMultiplePromosByIds", query = "from promotions where idPromo in (:ids)")
})
public class Promo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promo", updatable = false, nullable = false)
    private long idPromo;

    @Column(name = "discount", nullable = false)
    private int discount;

    @Column(name = "name", nullable = false)
    private String name;


    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIdPromo() {
        return idPromo;
    }

}
