package com.gmail.ezlotnikova.springboot.controller;

import java.util.List;
import java.util.Set;
import javax.validation.Valid;

import com.gmail.ezlotnikova.service.ItemService;
import com.gmail.ezlotnikova.service.ShopService;
import com.gmail.ezlotnikova.service.model.ItemDTO;
import com.gmail.ezlotnikova.service.model.ShopDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    private final ShopService shopService;
    private final ItemService itemService;

    public AppController(ShopService shopService, ItemService itemService) {
        this.shopService = shopService;
        this.itemService = itemService;
    }

    @GetMapping("/shops")
    public String showAllShops(Model model) {
        List<ShopDTO> shops = shopService.findAll();
        model.addAttribute("shops", shops);
        return "shops";
    }

    @GetMapping("/shops/add")
    public String showAddShopForm(Model model) {
        model.addAttribute("shop", new ShopDTO());
        return "add-shop";
    }

    @PostMapping("/shops/add")
    public String addShop(
            @Valid @ModelAttribute(name = "shop") ShopDTO shop,
            BindingResult errors) {
        if (errors.hasErrors()) {
            return "add-shop";
        } else {
            shopService.add(shop);
            return "redirect:/shops";
        }
    }

    @GetMapping("/items")
    public String showAllItems(Model model) {
        List<ItemDTO> items = itemService.findAll();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/items/{id}")
    public String showItemById(@PathVariable Long id, Model model) {
        ItemDTO item = itemService.findById(id);
        model.addAttribute("item", item);
        List<ShopDTO> shops = shopService.findShopsWithItem(id);
        model.addAttribute("shops", shops);
        return "item";
    }

    @GetMapping("/items/add")
    public String showAddItemForm(Model model) {
        model.addAttribute("item", new ItemDTO());
        List<ShopDTO> shops = shopService.findAll();
        model.addAttribute("shops", shops);
        return "add-item";
    }

    @PostMapping("/items/add")
    public String addItem(
            @RequestParam List<String> selectedShopIds,
            @Valid @ModelAttribute(name = "item") ItemDTO item,
            BindingResult errors) {
        if (errors.hasErrors()) {
            return "add-item";
        } else {
            ItemDTO addedItem = itemService.add(item);
            Long itemId = addedItem.getId();
            for (String id : selectedShopIds) {
                addItemToShop(itemId, Long.parseLong(id));
            }
            return "redirect:/items";
        }
    }

    @GetMapping("/items/delete/{id}")
    public String deleteItemById(@PathVariable Long id) {
        itemService.deleteItemById(id);
        return "redirect:/items";
    }

    private void addItemToShop(Long itemId, Long shopId) {
        ShopDTO shop = shopService.findById(shopId);
        Set<ItemDTO> items = shop.getItems();
        items.add(itemService.findById(itemId));
        shop.setItems(items);
        shopService.update(shop);
    }

}