package com.queempanadas.dao.jpa.implementations;

import com.queempanadas.dao.jpa.AbstractJPADAO;
import com.queempanadas.model.Sale;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SaleJPADAO extends AbstractJPADAO<Sale> {
    public SaleJPADAO() {
        super(Sale.class);
    }

    public List<Sale> getAll() {
        Query<Sale> query = getSession().createNamedQuery("GetAllSales", Sale.class);
        return query.getResultList();
    }

    public List<Sale> getAllBetweenDates(List<LocalDateTime> dates) {
        Query<Sale> query = getSession().createNamedQuery("GetSalesBetweenDates", Sale.class);
        query.setParameter("startDate", dates.get(0));
        query.setParameter("endDate",dates.get(1));
        return query.getResultList();
    }
}
