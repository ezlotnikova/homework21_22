package com.gmail.ezlotnikova.repository;

import java.math.BigDecimal;
import java.util.List;

import com.gmail.ezlotnikova.repository.model.Item;

public interface ItemRepository extends GenericRepository<Long, Item> {

    List<BigDecimal> getPricesList();

    List<Item> selectItemsByNameAndPrice(String name, BigDecimal minPrice, BigDecimal maxPrice);

}