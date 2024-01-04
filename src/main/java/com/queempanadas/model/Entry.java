package com.queempanadas.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.queempanadas.dao.jpa.LocalDateTimeConverter;
import com.queempanadas.utils.DateUtils;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "entries")
@NamedQueries(value = {
        @NamedQuery(name = "GetAllEntries", query = "from entries")
})
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entry", updatable = false, nullable = false)
    private long idEntry;

    @Enumerated(EnumType.STRING)
    @Column(name = "shift", nullable = false)
    private Shift shift = new DateUtils().parseLocalDateTimeToShift(LocalDateTime.now());

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "entry_date", nullable = false)
    private final LocalDateTime entryDate = LocalDateTime.now();

    @JsonManagedReference
    @OneToMany(mappedBy = "entry", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ProductEntry> productEntry = new ArrayList<ProductEntry>();

    public Entry(Shift shift, List<ProductEntry> productEntry) {
        this.shift = shift;
        this.productEntry = productEntry;
    }


    public Entry() {
    }

    public long getIdEntry() {
        return idEntry;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public List<ProductEntry> getProductEntry() {
        return productEntry;
    }

    public void setProductEntry(List<ProductEntry> productEntry) {
        this.productEntry = productEntry;
    }
}
