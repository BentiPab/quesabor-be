package com.queempanadas.dao.jpa.implementations;

import com.queempanadas.dao.jpa.AbstractJPADAO;
import com.queempanadas.model.Product;
import com.queempanadas.model.ProductType;
import com.queempanadas.model.Promo;
import com.queempanadas.model.Sale;
import org.hibernate.query.Query;

import java.util.List;

public class PromotionJPADAO extends AbstractJPADAO<Promo> {
    public PromotionJPADAO() {
        super(Promo.class);
    }

    public List<Promo> getAll() {
        Query<Promo> query = getSession().createNamedQuery("GetAllPromos", Promo.class);
        return query.getResultList();
    }

    public List<Promo> getMultipleByIds(List<Long> ids) {
        Query<Promo> query = getSession().createNamedQuery("GetMultiplePromosByIds", Promo.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }
}
