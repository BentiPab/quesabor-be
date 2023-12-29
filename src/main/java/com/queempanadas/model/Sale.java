package com.queempanadas.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.queempanadas.dao.jpa.LocalDateTimeConverter;
import com.queempanadas.utils.DateUtils;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "sales")
@NamedQueries(value = {
        @NamedQuery(name = "GetAllSales", query = "from sales")
})
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale", updatable = false, nullable = false)
    private long idSale;

    @Enumerated(EnumType.STRING)
    @Column(name = "shift", nullable = false)
    private Shift shift = new DateUtils().parseLocalDateTimeToShift(LocalDateTime.now());


    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "sale_date", nullable = false)
    private final LocalDateTime saleDate = LocalDateTime.now();

    @JsonManagedReference
    @OneToMany(mappedBy = "sale", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<EmpanadaSale> empanadasSale = new ArrayList<EmpanadaSale>();


    public Sale(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Sale() {

    }

    public long getIdSale() {
        return idSale;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public List<EmpanadaSale> getEmpanadasSale() {
        return empanadasSale;
    }

    public void setEmpanadasSale(List<EmpanadaSale> empanadasSale) {
        this.empanadasSale = empanadasSale;
    }
}
