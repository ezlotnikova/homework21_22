package com.gmail.ezlotnikova.service;

import java.util.List;

import com.gmail.ezlotnikova.service.model.ShopDTO;

public interface ShopService {

    void add(ShopDTO shop);

    List<ShopDTO> findAll();

}