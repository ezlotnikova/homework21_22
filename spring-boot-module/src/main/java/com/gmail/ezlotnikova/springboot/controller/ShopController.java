package com.gmail.ezlotnikova.springboot.controller;

import java.util.List;
import javax.validation.Valid;

import com.gmail.ezlotnikova.service.ShopService;
import com.gmail.ezlotnikova.service.model.ShopDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
