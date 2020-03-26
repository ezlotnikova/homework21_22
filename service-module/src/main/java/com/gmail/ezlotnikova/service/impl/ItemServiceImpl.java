package com.gmail.ezlotnikova.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import com.gmail.ezlotnikova.repository.ItemRepository;
import com.gmail.ezlotnikova.repository.model.Item;
import com.gmail.ezlotnikova.service.ItemService;
import com.gmail.ezlotnikova.service.converter.ItemConverter;
import com.gmail.ezlotnikova.service.model.ItemDTO;
import com.gmail.ezlotnikova.service.model.ItemWithShopsDTO;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemConverter itemConverter;

    public ItemServiceImpl(ItemRepository itemRepository, ItemConverter itemConverter) {
        this.itemRepository = itemRepository;
        this.itemConverter = itemConverter;
    }

    @Override
    @Transactional
    public void add(ItemDTO itemDTO) {
        Item item = itemConverter.getDTOFromDatabaseObject(itemDTO);
        itemRepository.persist(item);
    }

    @Override
    @Transactional
    public List<ItemDTO> findAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item : items) {
            ItemDTO itemDTO = itemConverter.getDatabaseObjectFromDTO(item);
            itemDTOList.add(itemDTO);
        }
        return itemDTOList;
    }

    @Override
    public ItemWithShopsDTO findById(Long id) {
        Item item = itemRepository.findById(id);
        return itemConverter.getItemWithShopsDTOFromItem(item);
    }

    @Override
    @Transactional
    public void deleteItemById(Long id) {
        itemRepository.remove(
                itemRepository.findById(id));
    }

}