package com.gmail.ezlotnikova.service.converter.impl;

import com.gmail.ezlotnikova.repository.model.Shop;
import com.gmail.ezlotnikova.service.converter.ShopConverter;
import com.gmail.ezlotnikova.service.model.ShopDTO;
import org.springframework.stereotype.Component;

@Component
public class ShopConverterImpl implements ShopConverter {

    @Override
    public ShopDTO getDatabaseObjectFromDTO(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        Long id = shop.getId();
        shopDTO.setId(id);
        String name = shop.getName();
        shopDTO.setName(name);
        String location = shop.getLocation();
        shopDTO.setLocation(location);
        return shopDTO;
    }

    @Override
    public Shop getDTOFromDatabaseObject(ShopDTO shopDTO) {
        Shop shop = new Shop();
        String name = shopDTO.getName();
        shop.setName(name);
        String location = shopDTO.getLocation();
        shop.setLocation(location);
        return shop;
    }

}