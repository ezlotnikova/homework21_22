package com.gmail.ezlotnikova.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import com.gmail.ezlotnikova.repository.ShopRepository;
import com.gmail.ezlotnikova.repository.model.Shop;
import com.gmail.ezlotnikova.service.ShopService;
import com.gmail.ezlotnikova.service.converter.ShopConverter;
import com.gmail.ezlotnikova.service.model.ShopDTO;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ShopConverter shopConverter;

    public ShopServiceImpl(ShopRepository shopRepository, ShopConverter shopConverter) {
        this.shopRepository = shopRepository;
        this.shopConverter = shopConverter;
    }

    @Override
    @Transactional
    public void add(ShopDTO shopDTO) {
        Shop shop = shopConverter.getDatabaseObjectFromDTO(shopDTO);
        shopRepository.persist(shop);
    }

    @Override
    @Transactional
    public List<ShopDTO> findAll() {
        List<Shop> shops = shopRepository.findAll();
        List<ShopDTO> shopDTOList = new ArrayList<>();
        for (Shop shop : shops) {
            ShopDTO shopDTO = shopConverter.getDTOFromDatabaseObject(shop);
            shopDTOList.add(shopDTO);
        }
        return shopDTOList;
    }

    @Transactional
    @Override
    public List<ShopDTO> findShopsByLocation(String location) {
        List<Shop> shops = shopRepository.findShopsByLocation(location);
        List<ShopDTO> shopDTOList = new ArrayList<>();
        for (Shop shop : shops) {
            ShopDTO shopDTO = shopConverter.getDTOFromDatabaseObject(shop);
            shopDTOList.add(shopDTO);
        }
        return shopDTOList;
    }

    @Override
    public List<String> getLocationsList() {
        return shopRepository.getLocationsList();
    }

}