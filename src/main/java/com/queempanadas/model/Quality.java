package com.queempanadas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "qualities")
@NamedQueries(value = {
        @NamedQuery(name = "GetAllQualities", query = "from qualities")
})
public class Quality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quality", updatable = false, nullable = false)
    private long idQuality;


    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;


    @JsonIgnoreProperties("quality")
    @OneToMany(mappedBy = "quality")
    private List<Empanada> empanadas = new ArrayList<Empanada>();


    public Quality(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Quality() {

    }

    public long getIdQuality() {
        return idQuality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Empanada> getEmpanadas() {
        return empanadas;
    }

    public void setEmpanadas(List<Empanada> empanadas) {
        this.empanadas = empanadas;
    }
}
