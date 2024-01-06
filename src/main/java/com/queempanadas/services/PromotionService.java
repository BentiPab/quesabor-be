package com.queempanadas.services;

import com.queempanadas.dao.jpa.implementations.PromotionJPADAO;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.model.Promo;

import java.util.List;

public class PromotionService extends AbstractService<Promo> {

    public PromotionService() {
        super(new PromotionJPADAO());
    }

    @Override
    protected void validate(Promo promo) throws FieldValidationException {

    }

    public List<Promo> getAll() {
        return ((PromotionJPADAO) this.dao).getAll();
    }

    public List<Promo> getMultipleById(List<Long> ids) {
        return ((PromotionJPADAO) this.dao).getMultipleByIds(ids);
    }
}
