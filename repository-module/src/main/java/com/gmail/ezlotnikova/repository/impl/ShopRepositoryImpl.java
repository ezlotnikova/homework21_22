package com.gmail.ezlotnikova.repository.impl;

import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.gmail.ezlotnikova.repository.ShopRepository;
import com.gmail.ezlotnikova.repository.model.Shop;
import org.springframework.stereotype.Repository;

@Repository
public class ShopRepositoryImpl extends GenericRepositoryImpl<Long, Shop> implements ShopRepository {

    @Override
    public List<Shop> findShopsByLocation(String location) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shop> query = builder.createQuery(Shop.class);
        Root<Shop> root = query.from(Shop.class);
        ParameterExpression<String> parameterExpression = builder.parameter(String.class);
        query.select(root)
                .where(builder.equal(root.get("location"), parameterExpression));
        TypedQuery<Shop> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(parameterExpression, location);
        return typedQuery.getResultList();
    }

    @Override
    public List<String> getLocationsList() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<Shop> root = query.from(Shop.class);
        query.select(root.get("location"))
                .distinct(true);
        TypedQuery<String> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

}