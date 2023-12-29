package com.queempanadas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "empanada_sale")
public class EmpanadaSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empanada_sale_id")
    private long id;

    @Column
    private String empanada;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_sale", nullable = false)
    private Sale sale;

    @Column
    private int quantity;

    @Column
    private int empanadaPrice;


    public EmpanadaSale() {

    }

    public EmpanadaSale(Empanada empanada, Sale sale, int quantity) {
        this.empanada = empanada.getName();
        this.quantity = quantity;
        this.sale = sale;
        this.empanadaPrice = empanada.getQuality().getPrice();
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public String getEmpanada() {
        return empanada;
    }

    public void setEmpanada(String empanada) {
        this.empanada = empanada;
    }

    public long getId() {
        return id;
    }

    public int getEmpanadaPrice() {
        return empanadaPrice;
    }

    public void setEmpanadaPrice(int empanadaPrice) {
        this.empanadaPrice = empanadaPrice;
    }
}
