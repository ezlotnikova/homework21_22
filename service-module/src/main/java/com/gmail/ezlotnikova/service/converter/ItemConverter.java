package com.gmail.ezlotnikova.service.converter;

import com.gmail.ezlotnikova.repository.model.Item;
import com.gmail.ezlotnikova.service.model.ItemDTO;
import com.gmail.ezlotnikova.service.model.ItemWithShopsDTO;

public interface ItemConverter extends GenericConverter<ItemDTO, Item> {

    ItemWithShopsDTO getItemWithShopsDTOFromItem(Item item);

}