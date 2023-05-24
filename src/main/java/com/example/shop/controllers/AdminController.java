package com.example.shop.controllers;

import com.example.shop.helpers.TimeHelper;
import com.example.shop.models.ItemModel;
import com.example.shop.repos.ItemRepo;
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

    public AdminController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping
    public String getAdmin(){
        return "admin";
    }

    @GetMapping("/edit")
    public String getAll(Model model){
        List<ItemModel> list = itemRepo.findAll();
        list = TimeHelper.getTime(list);
        model.addAttribute("items", list);
        return "editItems";
    }

}
