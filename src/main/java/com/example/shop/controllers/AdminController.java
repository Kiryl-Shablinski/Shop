package com.example.shop.controllers;

import com.example.shop.helpers.TimeHelper;
import com.example.shop.models.ItemModel;
import com.example.shop.models.RequestModel;
import com.example.shop.repos.ItemRepo;
import com.example.shop.repos.RequestRepo;
import com.example.shop.services.FireBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ItemRepo itemRepo;
    private final RequestRepo requestRepo;

    private final FireBaseService fireBaseService;


    public AdminController(ItemRepo itemRepo, RequestRepo requestRepo, FireBaseService fireBaseService) {
        this.itemRepo = itemRepo;
        this.requestRepo = requestRepo;
        this.fireBaseService = fireBaseService;
    }

    @GetMapping
    public String getAdmin(){
        return "admin";
    }

    @GetMapping("/edit")
    public String getAll(Model model){
        List<ItemModel> list = itemRepo.findAll();
        list = TimeHelper.getTime(list);
        list.stream()
                        .forEach(itemModel -> itemModel.setUrl(fireBaseService.getUrl(itemModel.getUrl())));
        model.addAttribute("items", list);
        return "editItems";
    }

    @GetMapping("/req")
    public String getPage(Model model){
        List<RequestModel> list = requestRepo.findAll();
        model.addAttribute("req", list);

        return "requests";
    }

}
