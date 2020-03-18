package com.gmail.ezlotnikova.service;

import java.util.List;

import com.gmail.ezlotnikova.service.model.ItemDTO;

public interface ItemService {

    ItemDTO add(ItemDTO item);

    ItemDTO findById(Long id);

    List<ItemDTO> findAll();

    void deleteItemById(Long id);

}