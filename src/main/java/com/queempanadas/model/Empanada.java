package com.queempanadas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity(name = "empanadas")
@NamedQueries(value = {
        @NamedQuery(name = "GetAllEmpanadas", query = "from empanadas"),
        @NamedQuery(name = "GetMultipleByIds", query = "from empanadas where idEmpanada in (:ids)")
})
public class Empanada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empanada", updatable = false, nullable = false)
    private long idEmpanada;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "safety_stock", nullable = false)
    private int safetyStock = 0;

    @Column(name = "stock", nullable = false)
    private int stock = 0;

    @JsonIgnoreProperties("empanadas")
    @ManyToOne
    @JoinColumn(name = "id_quality", nullable = false)
    private Quality quality;

    public Empanada() {

    }

    public Empanada(String name, int safetyStock, int stock) {
        this.name = name;
        this.safetyStock = safetyStock;
        this.stock = stock;
    }

    public Empanada(String name) {
        this.name = name;
    }

    public long getIdEmpanada() {
        return idEmpanada;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSafetyStock() {
        return safetyStock;
    }

    public void setSafetyStock(int safetyStock) {
        this.safetyStock = safetyStock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Quality getQuality() {
        return this.quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }
}
