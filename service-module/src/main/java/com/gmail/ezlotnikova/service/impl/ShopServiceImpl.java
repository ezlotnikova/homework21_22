package com.gmail.ezlotnikova.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
        Shop shop = shopConverter.convertDTOtoDatabaseObject(shopDTO);
        shopRepository.add(shop);
    }

    @Override
    @Transactional
    public void update(ShopDTO shopDTO) {
        Shop shop = shopConverter.convertDTOtoDatabaseObject(shopDTO);
        shopRepository.merge(shop);
    }

    @Override
    @Transactional
    public ShopDTO findById(Long id) {
        Shop shop = shopRepository.findById(id);
        ShopDTO shopDTO = shopConverter.convertDatabaseObjectToDTO(shop);
        return shopDTO;
    }

    @Override
    @Transactional
    public List<ShopDTO> findAll() {
        List<Shop> shops = shopRepository.findAll();
        List<ShopDTO> shopDTOList = new ArrayList<>();
        for (Shop shop : shops) {
            ShopDTO shopDTO = shopConverter.convertDatabaseObjectToDTO(shop);
            shopDTOList.add(shopDTO);
        }
        return shopDTOList;
    }

    @Override
    @Transactional
    public List<ShopDTO> findShopsWithItem(Long id) {
        return shopRepository.findShopsWithItem(id).stream()
                .map(shopConverter::convertDatabaseObjectToDTO)
                .collect(Collectors.toList());
    }

}