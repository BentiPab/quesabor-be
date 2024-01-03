package com.queempanadas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "product_entry")
public class ProductEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_entry_id")
    private long productEntryId;

    @Column
    private String product;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_entry", nullable = false)
    private Entry entry;

    @Column
    private int quantity;

    public ProductEntry() {
    }

    public ProductEntry(String product, Entry entry, int quantity) {
        this.product = product;
        this.entry = entry;
        this.quantity = quantity;
    }

    public long getProductEntryId() {
        return productEntryId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
