package com.gmail.ezlotnikova.service.converter.impl;

import java.math.BigDecimal;

import com.gmail.ezlotnikova.repository.model.Item;
import com.gmail.ezlotnikova.repository.model.ItemDetails;
import com.gmail.ezlotnikova.service.converter.ItemConverter;
import com.gmail.ezlotnikova.service.model.ItemDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemConverterImpl implements ItemConverter {

    @Override
    public ItemDTO convertDatabaseObjectToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        Long id = item.getId();
        itemDTO.setId(id);
        String name = item.getName();
        itemDTO.setName(name);
        String description = item.getDescription();
        itemDTO.setDescription(description);
        ItemDetails itemDetails = item.getItemDetails();
        BigDecimal price = itemDetails.getPrice();
        itemDTO.setPrice(price);
        return itemDTO;
    }

    @Override
    public Item convertDTOtoDatabaseObject(ItemDTO itemDTO) {
        Item item = new Item();
        if (itemDTO.getId() != null) {
            item.setId(itemDTO.getId());
        }
        String name = itemDTO.getName();
        item.setName(name);
        String description = itemDTO.getDescription();
        item.setDescription(description);
        BigDecimal price = itemDTO.getPrice();
        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setPrice(price);
        itemDetails.setItem(item);
        item.setItemDetails(itemDetails);
        return item;
    }

}