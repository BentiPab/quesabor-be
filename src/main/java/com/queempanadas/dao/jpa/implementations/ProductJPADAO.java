package com.queempanadas.dao.jpa.implementations;

import com.queempanadas.dao.jpa.AbstractJPADAO;
import com.queempanadas.model.Product;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductJPADAO extends AbstractJPADAO<Product> {

    public ProductJPADAO() {
        super(Product.class);
    }

    public List<Product> getAll() {
        Query<Product> query = getSession().createNamedQuery("GetAllProducts", Product.class);
        return query.getResultList();
    }

    public List<Product> getMultipleByIds(List<Long> ids) {
        Query<Product> query = getSession().createNamedQuery("GetMultipleProductsByIds", Product.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

}
