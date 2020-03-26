package com.gmail.ezlotnikova.service.converter.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gmail.ezlotnikova.repository.ShopRepository;
import com.gmail.ezlotnikova.repository.model.Item;
import com.gmail.ezlotnikova.repository.model.ItemDetails;
import com.gmail.ezlotnikova.repository.model.Shop;
import com.gmail.ezlotnikova.service.converter.ItemConverter;
import com.gmail.ezlotnikova.service.converter.ShopConverter;
import com.gmail.ezlotnikova.service.model.ItemDTO;
import com.gmail.ezlotnikova.service.model.ItemWithShopsDTO;
import com.gmail.ezlotnikova.service.model.ShopDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemConverterImpl implements ItemConverter {

    private final ShopRepository shopRepository;
    private final ShopConverter shopConverter;

    public ItemConverterImpl(ShopRepository shopRepository, ShopConverter shopConverter) {
        this.shopRepository = shopRepository;
        this.shopConverter = shopConverter;
    }

    @Override
    public ItemDTO getDatabaseObjectFromDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        Long id = item.getId();
        itemDTO.setId(id);
        String name = item.getName();
        itemDTO.setName(name);
        String description = item.getDescription();
        itemDTO.setDescription(description);
        ItemDetails itemDetails = item.getItemDetails();
        if (itemDetails != null) {
            BigDecimal price = itemDetails.getPrice();
            itemDTO.setPrice(price);
        }
        List<Long> shopIds = new ArrayList<>();
        Set<Shop> shops = item.getShops();
        for (Shop shop : shops) {
            shopIds.add(shop.getId());
        }
        itemDTO.setShopIds(shopIds);
        return itemDTO;
    }

    @Override
    public Item getDTOFromDatabaseObject(ItemDTO itemDTO) {
        Item item = new Item();
        String name = itemDTO.getName();
        item.setName(name);
        String description = itemDTO.getDescription();
        item.setDescription(description);
        BigDecimal price = itemDTO.getPrice();
        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setPrice(price);
        itemDetails.setItem(item);
        item.setItemDetails(itemDetails);
        List<Long> shopIds = itemDTO.getShopIds();
        Set<Shop> shops = new HashSet<>();
        for (Long id : shopIds) {
            Shop shop = shopRepository.findById(id);
            shops.add(shop);
        }
        item.setShops(shops);
        return item;
    }

    @Override
    public ItemWithShopsDTO getItemWithShopsDTOFromItem(Item item) {
        ItemWithShopsDTO itemWithShops = new ItemWithShopsDTO();
        Long id = item.getId();
        itemWithShops.setId(id);
        String name = item.getName();
        itemWithShops.setName(name);
        String description = item.getDescription();
        itemWithShops.setDescription(description);
        ItemDetails itemDetails = item.getItemDetails();
        if (itemDetails != null) {
            BigDecimal price = itemDetails.getPrice();
            itemWithShops.setPrice(price);
        }
        List<ShopDTO> shopDTOs = new ArrayList<>();
        Set<Shop> shops = item.getShops();
        for (Shop shop : shops) {
            ShopDTO shopDTO = shopConverter.getDatabaseObjectFromDTO(shop);
            shopDTOs.add(shopDTO);
        }
        itemWithShops.setShops(shopDTOs);
        return itemWithShops;
    }

}