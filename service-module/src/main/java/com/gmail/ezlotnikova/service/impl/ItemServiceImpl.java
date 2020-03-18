package com.gmail.ezlotnikova.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;

import com.gmail.ezlotnikova.repository.ItemRepository;
import com.gmail.ezlotnikova.repository.ShopRepository;
import com.gmail.ezlotnikova.repository.model.Item;
import com.gmail.ezlotnikova.repository.model.Shop;
import com.gmail.ezlotnikova.service.ItemService;
import com.gmail.ezlotnikova.service.converter.ItemConverter;
import com.gmail.ezlotnikova.service.model.ItemDTO;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemConverter itemConverter;
    private final ShopRepository shopRepository;

    public ItemServiceImpl(ItemRepository itemRepository, ItemConverter itemConverter, ShopRepository shopRepository) {
        this.itemRepository = itemRepository;
        this.itemConverter = itemConverter;
        this.shopRepository = shopRepository;
    }

    @Override
    @Transactional
    public ItemDTO add(ItemDTO itemDTO) {
        Item item = itemConverter.convertDTOtoDatabaseObject(itemDTO);
        Item addedItem = itemRepository.add(item);
        ItemDTO addedItemDTO = itemConverter.convertDatabaseObjectToDTO(addedItem);
        return addedItemDTO;
    }

    @Override
    public ItemDTO findById(Long id) {
        Item item = itemRepository.findById(id);
        ItemDTO itemDTO = itemConverter.convertDatabaseObjectToDTO(item);
        return itemDTO;
    }

    @Override
    @Transactional
    public List<ItemDTO> findAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item : items) {
            ItemDTO itemDTO = itemConverter.convertDatabaseObjectToDTO(item);
            itemDTOList.add(itemDTO);
        }
        return itemDTOList;
    }

    @Override
    @Transactional
    public void deleteItemById(Long id) {
        deleteItemFromAllShops(id);
        itemRepository.remove(
                itemRepository.findById(id));
    }

    private void deleteItemFromAllShops(Long id) {
        List<Shop> shops = shopRepository.findShopsWithItem(id);
        for (Shop shop : shops) {
            Set<Item> items = shop.getItems();
            items.removeIf(item -> item.getId().equals(id));
        }
    }

}