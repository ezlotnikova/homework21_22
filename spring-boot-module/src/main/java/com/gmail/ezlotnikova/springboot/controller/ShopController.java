package com.gmail.ezlotnikova.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import com.gmail.ezlotnikova.service.ShopService;
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
@RequestMapping("/shops")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    public String showAllShops(Model model) {
        List<ShopDTO> shops = shopService.findAll();
        model.addAttribute("shops", shops);
        return "shops";
    }

    @GetMapping("/add")
    public String showAddShopForm(Model model) {
        model.addAttribute("shop", new ShopDTO());
        return "add_shop";
    }

    @PostMapping("/add")
    public String addShop(
            @Valid @ModelAttribute(name = "shop") ShopDTO shop,
            BindingResult errors) {
        if (errors.hasErrors()) {
            return "add_shop";
        } else {
            shopService.add(shop);
            return "redirect:/shops";
        }
    }

    @GetMapping("/search")
    public String showSearchForm(Model model) {
        List<String> locations = shopService.getLocationsList();
        model.addAttribute("locations", locations);
        return "shop_search_form";
    }

    @GetMapping(value = "/search", params = "location")
    public ModelAndView showShopsByLocation(
            @RequestParam(value = "location") String location) {
        List<ShopDTO> selectedShops = shopService.findShopsByLocation(location);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("shops", selectedShops);
        return new ModelAndView("shops", attributes);
    }

}
