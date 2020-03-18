package com.gmail.ezlotnikova.repository.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.gmail.ezlotnikova.repository.ItemRepository;
import com.gmail.ezlotnikova.repository.ShopRepository;
import com.gmail.ezlotnikova.repository.model.Item;
import com.gmail.ezlotnikova.repository.model.Shop;
import org.springframework.stereotype.Repository;

@Repository
public class ShopRepositoryImpl extends GenericRepositoryImpl<Long, Shop> implements ShopRepository {

    private final ItemRepository itemRepository;

    public ShopRepositoryImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Shop> findShopsWithItem(Long id) {
        Item item = itemRepository.findById(id);
        return findAll().stream()
                .filter(shop -> shop.getItems().contains(item))
                .collect(Collectors.toList());
    }

}