package com.gmail.ezlotnikova.repository.impl;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Query;

import com.gmail.ezlotnikova.repository.ItemRepository;
import com.gmail.ezlotnikova.repository.model.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl extends GenericRepositoryImpl<Long, Item> implements ItemRepository {

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<BigDecimal> getPricesList() {
        String qhl = "SELECT i.price FROM ItemDetails AS i";
        Query query = entityManager.createQuery(qhl);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Item> selectItemsByNameAndPrice(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        String qhl = "FROM Item as I " +
                "WHERE I.name LIKE :name " +
                "AND (:minPrice is null or I.itemDetails.price >= :minPrice) " +
                "AND (:maxPrice is null or I.itemDetails.price <= :maxPrice) " +
                "ORDER BY I.name ASC";
        Query query = entityManager.createQuery(qhl);
        query.setParameter("name", "%" + name + "%");
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return query.getResultList();
    }

}