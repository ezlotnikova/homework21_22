package com.gmail.ezlotnikova.service;

import java.util.List;

import com.gmail.ezlotnikova.service.model.ShopDTO;

public interface ShopService {

    void add(ShopDTO shop);

    ShopDTO findById(Long id);

    void update(ShopDTO shop);

    List<ShopDTO> findAll();

    List<ShopDTO> findShopsWithItem(Long id);

}