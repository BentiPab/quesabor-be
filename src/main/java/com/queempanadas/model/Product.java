package com.queempanadas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity(name = "products")
@NamedQueries(value = {
        @NamedQuery(name = "GetAllProducts", query = "from products"),
        @NamedQuery(name = "GetMultipleByIds", query = "from products where idProduct in (:ids)")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", updatable = false, nullable = false)
    private long idProduct;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "safety_stock", nullable = false)
    private int safetyStock = 0;

    @Column(name = "stock", nullable = false)
    private int stock = 0;

    @ManyToOne
    @JsonIgnoreProperties("products")
    @JoinColumn(name = "id_product_type", nullable = false)
    private ProductType productType;

    public Product() {

    }

    public Product(String name, int safetyStock, int stock) {
        this.name = name;
        this.safetyStock = safetyStock;
        this.stock = stock;
    }

    public Product(String name) {
        this.name = name;
    }

    public long getIdProduct() {
        return idProduct;
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

    public ProductType getQuality() {
        return this.productType;
    }

    public void setQuality(ProductType productType) {
        this.productType = productType;
    }
}
