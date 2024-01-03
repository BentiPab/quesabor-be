package com.queempanadas.services;

import com.queempanadas.dao.jpa.implementations.ProductJPADAO;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends AbstractService<Product> {

    public ProductService() {
        super(new ProductJPADAO());
    }

    @Override
    protected void validate(Product product) throws FieldValidationException {

    }

    public List<Product> getMultipleById(List<Long> ids) {
        return ((ProductJPADAO) this.dao).getMultipleByIds(ids);
    }

    public List<Product> getAll() {
        return ((ProductJPADAO) this.dao).getAll();
    }
}
