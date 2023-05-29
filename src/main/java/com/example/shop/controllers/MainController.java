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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class MainController {

    private final ItemRepo itemRepo;
    @Autowired
    FireBaseService fireBaseService;

    public MainController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping
    public String getMainPage(Model model){
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().limit(5).collect(Collectors.toList());
        list = TimeHelper.getTime(list);
        list.stream()
                        .forEach(itemModel -> itemModel.setUrl(fireBaseService.getUrl(itemModel.getUrl())));
        model.addAttribute("items", list);

        return "index";
    }
}
