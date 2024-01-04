package com.queempanadas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "product_sale")
public class ProductSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product_sale")
    private long productSaleId;

    @Column
    private String product;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_sale", nullable = false)
    private Sale sale;

    @Column
    private int quantity;

    @Column
    private int productPrice;


    public ProductSale() {

    }

    public ProductSale(Product product, Sale sale, int quantity) {
        this.product = product.getName();
        this.quantity = quantity;
        this.sale = sale;
        this.productPrice = product.getProductType().getPrice();
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public long getProductSaleId() {
        return productSaleId;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}
