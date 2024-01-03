package com.queempanadas.services;

import com.queempanadas.dao.jpa.implementations.ProductTypeJPADAO;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.model.ProductType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService extends  AbstractService<ProductType>{

    public ProductTypeService() {
        super(new ProductTypeJPADAO());
    }

    @Override
    protected void validate(ProductType productType) throws FieldValidationException {

    }
    public List<ProductType> getAll() {
        return ((ProductTypeJPADAO)this.dao).getAll();
    }
}
