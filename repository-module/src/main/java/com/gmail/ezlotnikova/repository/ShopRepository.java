package com.gmail.ezlotnikova.repository;

import java.util.List;

import com.gmail.ezlotnikova.repository.model.Shop;

public interface ShopRepository extends GenericRepository<Long, Shop> {

    List<Shop> findShopsWithItem(Long id);

}