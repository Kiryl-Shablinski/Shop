package com.example.shop.controllers;

import com.example.shop.helpers.TimeHelper;
import com.example.shop.models.ItemModel;
import com.example.shop.repos.ItemRepo;
import com.example.shop.services.FireBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/allItems")
public class AllItemsController {

    private final ItemRepo itemRepo;

    private final FireBaseService fireBaseService;

    public AllItemsController(ItemRepo itemRepo, FireBaseService fireBaseService) {
        this.itemRepo = itemRepo;
        this.fireBaseService = fireBaseService;
    }

    @GetMapping
    public String getAllItemsPage(Model model){
        List<ItemModel> list = itemRepo.findAll();
        model.addAttribute("items", list);
        list.stream().forEach(itemModel -> itemModel.setUrl(fireBaseService.getUrl(itemModel.getUrl())));
        list = TimeHelper.getTime(list);


        return "allItems";
    }
}
