package com.gmail.ezlotnikova.springboot.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView addItem(
            @Valid @ModelAttribute(name = "item") ItemDTO item,
            BindingResult errors) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("item", item);
        attributes.put("shops", shopService.findAll());
        if (errors.hasErrors()) {
            return new ModelAndView("add_item", attributes);
        } else {
            itemService.add(item);
            return new ModelAndView("redirect:/items");
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

    @GetMapping("/search")
    public String showSearchForm(Model model) {
        List<BigDecimal> prices = itemService.getPricesList();
        model.addAttribute("prices", prices);
        return "item_search_form";
    }

    @GetMapping(value = "/search", params = {"name", "minPrice", "maxPrice"})
    public ModelAndView showShopsByLocation(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice
    ) {
        System.out.println("MIN PRICE " + minPrice);
        System.out.println("MAX PRICE " + maxPrice);
        List<ItemDTO> selectedItems = itemService.selectItemsByNameAndPrice(name, minPrice, maxPrice);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("items", selectedItems);
        return new ModelAndView("items", attributes);
    }

}