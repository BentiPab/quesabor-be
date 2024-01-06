package com.queempanadas.services;

import com.queempanadas.dao.jpa.implementations.SaleJPADAO;
import com.queempanadas.dto.NewSaleDto;
import com.queempanadas.exceptions.AbstractException;
import com.queempanadas.exceptions.FieldValidationException;
import com.queempanadas.exceptions.ProductsNotFoundException;
import com.queempanadas.exceptions.PromosNotFoundException;
import com.queempanadas.model.*;
import com.queempanadas.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        List<Long> prodsIds = newSaleDto.getEmpanadaQty()
                .keySet()
                .stream()
                .toList();

        List<Product> products = new ProductService().getMultipleById(prodsIds);

        if (products.size() == 0) {
            throw  new ProductsNotFoundException();
        }

        List<Promo> promos = new PromotionService().getMultipleById(newSaleDto.getPromotions());

        if (promos.size() == 0 && newSaleDto.getPromotions().size() > 0) {
            throw new PromosNotFoundException();
        }

        List<ProductSale> empanadaSales = products.stream()
                .reduce(new ArrayList<ProductSale>(), (empanadaSaleArrayList, empanada) -> {
                    empanada.setStock(empanada.getStock() - newSaleDto.getEmpanadaQty()
                            .get(empanada.getIdProduct()));
                    empanadaSaleArrayList.add(new ProductSale(empanada, newSale, newSaleDto.getEmpanadaQty()
                            .get(empanada.getIdProduct())));
                    return empanadaSaleArrayList;
                }, (a, b) -> {
                    return a;
                });

        List<PromotionSale> promotionSales = promos.stream().reduce(new ArrayList<PromotionSale>(), (promoSaleArrayList, promo) -> {
            promoSaleArrayList.add(new PromotionSale(newSale, promo));

                    return promoSaleArrayList;
        }, (a,b)-> a);
        int saleTotal = products.stream()
                .reduce(0, (total, empanada) -> {
                            total += empanada.getProductType()
                                    .getPrice();
                            return total;
                        }, (a, b) -> a
                );
        int totalDiscount = promos.stream()
                .reduce(0, (total, promo) -> {
                    total += promo.getDiscount();
                    return total;
                }, (a, b) -> a);

        newSale.setPromotionSale(promotionSales);
        newSale.setProductSale(empanadaSales);
        newSale.setSaleTotal(saleTotal - totalDiscount);

        return ((SaleJPADAO) this.dao).persist(newSale);
    }

    public List<Sale> getAll() {
        return ((SaleJPADAO) this.dao).getAll();
    }

    public List<Sale> getAllBetweenDates(String type) {
        List<LocalDateTime> dates = new DateUtils().getDatesGap(type);
        return ((SaleJPADAO) this.dao).getAllBetweenDates(dates);
    }
}
