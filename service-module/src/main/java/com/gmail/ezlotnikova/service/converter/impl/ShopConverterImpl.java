package com.gmail.ezlotnikova.service.converter.impl;

import java.util.HashSet;
import java.util.Set;

import com.gmail.ezlotnikova.repository.model.Item;
import com.gmail.ezlotnikova.repository.model.Shop;
import com.gmail.ezlotnikova.service.converter.ItemConverter;
import com.gmail.ezlotnikova.service.converter.ShopConverter;
import com.gmail.ezlotnikova.service.model.ItemDTO;
import com.gmail.ezlotnikova.service.model.ShopDTO;
import org.springframework.stereotype.Component;

@Component
public class ShopConverterImpl implements ShopConverter {

    private final ItemConverter itemConverter;

    public ShopConverterImpl(ItemConverter itemConverter) {
        this.itemConverter = itemConverter;
    }

    @Override
    public ShopDTO convertDatabaseObjectToDTO(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        Long id = shop.getId();
        shopDTO.setId(id);
        String name = shop.getName();
        shopDTO.setName(name);
        String location = shop.getLocation();
        shopDTO.setLocation(location);
        Set<Item> items = shop.getItems();
        Set<ItemDTO> itemDTOSet = new HashSet<>();
        for (Item item : items) {
            ItemDTO itemDTO = itemConverter.convertDatabaseObjectToDTO(item);
            itemDTOSet.add(itemDTO);
        }
        shopDTO.setItems(itemDTOSet);
        return shopDTO;
    }

    @Override
    public Shop convertDTOtoDatabaseObject(ShopDTO shopDTO) {
        Shop shop = new Shop();
        if (shopDTO.getId() != null) {
            shop.setId(shopDTO.getId());
        }
        String name = shopDTO.getName();
        shop.setName(name);
        String location = shopDTO.getLocation();
        shop.setLocation(location);
        if (shopDTO.getItems() != null) {
            Set<ItemDTO> itemDTOSet = shopDTO.getItems();
            Set<Item> items = new HashSet<>();
            for (ItemDTO itemDTO : itemDTOSet) {
                Item item = itemConverter.convertDTOtoDatabaseObject(itemDTO);
                items.add(item);
            }
            shop.setItems(items);
        }
        return shop;
    }

}