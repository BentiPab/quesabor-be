package com.queempanadas.services;

import com.queempanadas.dao.jpa.implementations.EmpanadaJPADAO;
import com.queempanadas.dao.jpa.implementations.QualityJPADAO;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.model.Empanada;
import com.queempanadas.model.Quality;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualityService extends  AbstractService<Quality>{

    public QualityService() {
        super(new QualityJPADAO());
    }

    @Override
    protected void validate(Quality quality) throws FieldValidationException {

    }
    public List<Quality> getAll() {
        return ((QualityJPADAO)this.dao).getAll();
    }
}
