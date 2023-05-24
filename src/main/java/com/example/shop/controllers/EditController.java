package com.example.shop.controllers;

import com.example.shop.models.ItemModel;
import com.example.shop.repos.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edit")
public class EditController {

    final
    ItemRepo itemRepo;

    public EditController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable long id, Model model){
        ItemModel itemModel = itemRepo.findById(id);
        model.addAttribute("item", itemModel);
        return "edit";
    }
}
