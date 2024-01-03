package com.queempanadas.dao.jpa.implementations;

import com.queempanadas.dao.jpa.AbstractJPADAO;
import com.queempanadas.model.ProductType;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductTypeJPADAO extends AbstractJPADAO<ProductType> {


    public ProductTypeJPADAO() {
        super(ProductType.class);
    }


    public List<ProductType> getAll() {
        Query<ProductType> query = getSession().createNamedQuery("GetAllProductTypes", ProductType.class);
        return query.getResultList();
    }

}
