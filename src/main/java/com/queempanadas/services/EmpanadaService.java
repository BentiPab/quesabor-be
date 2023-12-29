package com.queempanadas.services;

import com.queempanadas.dao.jpa.implementations.EmpanadaJPADAO;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.model.Empanada;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpanadaService extends AbstractService<Empanada> {

    public EmpanadaService() {
        super(new EmpanadaJPADAO());
    }

    @Override
    protected void validate(Empanada empanada) throws FieldValidationException {

    }

    public List<Empanada> getMultipleById(List<Long> ids) {
        return ((EmpanadaJPADAO) this.dao).getMultipleByIds(ids);
    }

    public List<Empanada> getAll() {
        return ((EmpanadaJPADAO) this.dao).getAll();
    }
}
