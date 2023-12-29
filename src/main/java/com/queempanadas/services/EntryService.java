package com.queempanadas.services;

import com.queempanadas.dao.jpa.implementations.EntryJPADAO;
import com.queempanadas.exceptions.AbstractException;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.model.Empanada;
import com.queempanadas.model.EmpanadaEntry;
import com.queempanadas.model.Entry;
import com.queempanadas.dto.NewEntryDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntryService extends AbstractService<Entry> {

    public EntryService() {
        super(new EntryJPADAO());
    }

    @Override
    protected void validate(Entry entry) throws FieldValidationException {

    }

    public List<Entry> getAll() {
        return ((EntryJPADAO) this.dao).getAll();
    }

    public Entry createEntry(NewEntryDto entryBody) throws AbstractException {
        Entry newEntry = new Entry();
        List<Long> ids = entryBody.getEmpanadaQty()
                .keySet()
                .stream()
                .toList();
        List<Empanada> empanadas = new EmpanadaService().getMultipleById(ids);

        List<EmpanadaEntry> empanadaSales = empanadas.stream()
                .reduce(new ArrayList<EmpanadaEntry>(), (empanadaSaleArrayList, empanada) -> {
                    empanada.setStock(empanada.getStock() + entryBody.getEmpanadaQty()
                            .get(empanada.getIdEmpanada()));
                    empanadaSaleArrayList.add(new EmpanadaEntry(empanada.getName(), newEntry, entryBody.getEmpanadaQty()
                            .get(empanada.getIdEmpanada())));
                    return empanadaSaleArrayList;
                }, (a, b) -> {
                    return a;
                });
        newEntry.setEmpanadasEntry(empanadaSales);

        return ((EntryJPADAO) this.dao).persist(newEntry);
    }
}
