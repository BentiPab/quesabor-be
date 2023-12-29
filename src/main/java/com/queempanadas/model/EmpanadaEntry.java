package com.queempanadas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "empanada_entry")
public class EmpanadaEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empanada_entry_id")
    private long id;

    @Column
    private String empanada;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_entry", nullable = false)
    private Entry entry;

    @Column
    private int quantity;

    public EmpanadaEntry() {
    }

    public EmpanadaEntry(String empanada, Entry entry, int quantity) {
        this.empanada = empanada;
        this.entry = entry;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public String getEmpanada() {
        return empanada;
    }

    public void setEmpanada(String empanada) {
        this.empanada = empanada;
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
