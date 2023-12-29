package com.queempanadas.services;

import com.queempanadas.dao.jpa.implementations.SaleJPADAO;
import com.queempanadas.exceptions.AbstractException;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.model.Empanada;
import com.queempanadas.model.EmpanadaSale;
import com.queempanadas.dto.NewSaleDto;
import com.queempanadas.model.Sale;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService extends AbstractService<Sale> {
    public SaleService() {
        super(new SaleJPADAO());
    }

    @Override
    protected void validate(Sale sale) throws FieldValidationException {

    }

    public Sale createSale(NewSaleDto newSaleDto) throws AbstractException {
        Sale newSale = new Sale();
        newSale.setPaymentMethod(newSaleDto.getPaymentMethod());
        List<Long> ids = newSaleDto.getEmpanadaQty()
                .keySet()
                .stream()
                .toList();
        List<Empanada> empanadas = new EmpanadaService().getMultipleById(ids);

        List<EmpanadaSale> empanadaSales = empanadas.stream()
                .reduce(new ArrayList<EmpanadaSale>(), (empanadaSaleArrayList, empanada) -> {
                    empanada.setStock(empanada.getStock() - newSaleDto.getEmpanadaQty().get(empanada.getIdEmpanada()));
                    empanadaSaleArrayList.add(new EmpanadaSale(empanada, newSale, newSaleDto.getEmpanadaQty()
                            .get(empanada.getIdEmpanada())));
                    return empanadaSaleArrayList;
                }, (a, b) -> {
                    return a;
                });
        newSale.setEmpanadasSale(empanadaSales);

        return ((SaleJPADAO) this.dao).persist(newSale);
    }

    public List<Sale> getAll() {
        return ((SaleJPADAO) this.dao).getAll();
    }
}
