package com.queempanadas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "product_types")
@NamedQueries(value = {
        @NamedQuery(name = "GetAllProductTypes", query = "from product_types")
})
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product_type", updatable = false, nullable = false)
    private long idProductType;


    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;


    @OneToMany(mappedBy = "productType")
    private List<Product> products = new ArrayList<Product>();


    public ProductType(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public ProductType() {

    }

    public long getIdProductType() {
        return idProductType;
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

    @JsonIgnoreProperties("productType")
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
