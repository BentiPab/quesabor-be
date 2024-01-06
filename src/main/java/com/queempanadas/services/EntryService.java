package com.queempanadas.services;

import com.queempanadas.dao.jpa.implementations.EntryJPADAO;
import com.queempanadas.exceptions.AbstractException;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.exceptions.ProductsNotFoundException;
import com.queempanadas.model.Product;
import com.queempanadas.model.ProductEntry;
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

    public Entry createEntry(NewEntryDto entryBody) throws AbstractException, ProductsNotFoundException {
        Entry newEntry = new Entry();
        List<Long> ids = entryBody.getEmpanadaQty()
                .keySet()
                .stream()
                .toList();
        List<Product> products = new ProductService().getMultipleById(ids);
        if (products.size() == 0) {
            throw new ProductsNotFoundException();
        }
        List<ProductEntry> empanadaSales = products.stream()
                .reduce(new ArrayList<ProductEntry>(), (empanadaSaleArrayList, empanada) -> {
                    empanada.setStock(empanada.getStock() + entryBody.getEmpanadaQty()
                            .get(empanada.getIdProduct()));
                    empanadaSaleArrayList.add(new ProductEntry(empanada.getName(), newEntry, entryBody.getEmpanadaQty()
                            .get(empanada.getIdProduct())));
                    return empanadaSaleArrayList;
                }, (a, b) -> {
                    return a;
                });
        newEntry.setProductEntry(empanadaSales);

        return ((EntryJPADAO) this.dao).persist(newEntry);
    }
}
