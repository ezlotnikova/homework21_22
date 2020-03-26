package com.gmail.ezlotnikova.springboot.controller;

import java.util.List;
import javax.validation.Valid;

import com.gmail.ezlotnikova.service.ItemService;
import com.gmail.ezlotnikova.service.ShopService;
import com.gmail.ezlotnikova.service.model.ItemDTO;
import com.gmail.ezlotnikova.service.model.ItemWithShopsDTO;
import com.gmail.ezlotnikova.service.model.ShopDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ShopService shopService;
    private final ItemService itemService;

    public ItemController(ShopService shopService, ItemService itemService) {
        this.shopService = shopService;
        this.itemService = itemService;
    }

    @GetMapping()
    public String showAllItems(Model model) {
        List<ItemDTO> items = itemService.findAll();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/add")
    public String showAddItemForm(Model model) {
        model.addAttribute("item", new ItemDTO());
        List<ShopDTO> shops = shopService.findAll();
        model.addAttribute("shops", shops);
        return "add_item";
    }

    @PostMapping("/add")
    public String addItem(
            @Valid @ModelAttribute(name = "item") ItemDTO item,
            BindingResult errors) {
        if (errors.hasErrors()) {
            return "add_item";
        } else {
            itemService.add(item);
            return "redirect:/items";
        }
    }

    @GetMapping("/{id}")
    public String showItemById(@PathVariable Long id, Model model) {
        ItemWithShopsDTO item = itemService.findById(id);
        model.addAttribute("item", item);
        List<ShopDTO> shops = item.getShops();
        model.addAttribute("shops", shops);
        return "item";
    }

    @GetMapping("/delete/{id}")
    public String deleteItemById(@PathVariable Long id) {
        itemService.deleteItemById(id);
        return "redirect:/items";
    }

}