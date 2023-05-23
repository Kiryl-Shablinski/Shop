package com.example.shop.controllers;

import com.example.shop.helpers.ItemModelWithCurrency;
import com.example.shop.models.ItemModel;
import com.example.shop.models.RequestModel;
import com.example.shop.repos.ItemRepo;
import com.example.shop.repos.RequestRepo;
import com.example.shop.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Currency;

@Controller
@RequestMapping("/item")
public class DetailItemController {
   final
   ItemRepo itemRepo;

   final
   CurrencyService currencyService;
   @Autowired
    RequestRepo requestRepo;

    public DetailItemController(ItemRepo itemRepo, CurrencyService currencyService) {
        this.itemRepo = itemRepo;
        this.currencyService = currencyService;
    }

    @GetMapping("/{id}")
    public String getDetailPage(@PathVariable long id, Model model){
        ItemModel itemModel = itemRepo.findById(id);
        ItemModelWithCurrency itemModelWithCurrency = new ItemModelWithCurrency(itemModel);
        itemModelWithCurrency.setEurPrice(currencyService.getEurPrice(itemModel.getPrice()));
        itemModelWithCurrency.setUsdPrice(currencyService.getUsdPrice(itemModel.getPrice()));
        model.addAttribute("item", itemModelWithCurrency);
        return "detailItem";
    }
    @PostMapping("/{id}")
    public RedirectView saveDate(@PathVariable Long id, @RequestParam String name,
                                 @RequestParam String phoneNumber){
        RequestModel requestModel = new RequestModel();
        requestModel.setItemId(id);
        requestModel.setName(name);
        requestModel.setPhoneNumber(phoneNumber);
        requestRepo.save(requestModel);
        return new RedirectView("/allItems");
    }
}
