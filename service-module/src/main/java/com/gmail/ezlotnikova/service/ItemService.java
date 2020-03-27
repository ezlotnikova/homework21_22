package com.gmail.ezlotnikova.service;

import java.math.BigDecimal;
import java.util.List;

import com.gmail.ezlotnikova.service.model.ItemDTO;
import com.gmail.ezlotnikova.service.model.ItemWithShopsDTO;

public interface ItemService {

    void add(ItemDTO item);

    ItemWithShopsDTO findById(Long id);

    List<ItemDTO> findAll();

    void deleteItemById(Long id);

    List<BigDecimal> getPricesList();

    List<ItemDTO> selectItemsByNameAndPrice(String name, BigDecimal minPrice, BigDecimal maxPrice);

}