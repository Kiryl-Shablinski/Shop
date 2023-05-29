package com.example.shop.controllers;

import com.example.shop.models.ItemModel;
import com.example.shop.repos.ItemRepo;
import com.example.shop.services.FireBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/edit")
public class EditController {

    final
    ItemRepo itemRepo;

    @Autowired
    FireBaseService fireBaseService;

    public EditController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable long id, Model model){
        ItemModel itemModel = itemRepo.findById(id);
        itemModel.setUrl(fireBaseService.getUrl(itemModel.getUrl()));
        model.addAttribute("item", itemModel);
        return "edit";
    }

    @PostMapping("/{id}")
    public RedirectView setChanges(@PathVariable long id,
                                   @RequestParam String name,
                                   @RequestParam double price,
                                   @RequestParam String disc,
                                   @RequestParam String url){
        ItemModel itemModel = itemRepo.findById(id);
        itemModel.setName(name);
        itemModel.setPrice(price);
        itemModel.setUrl(url);
        itemModel.setDisc(disc);
        itemRepo.save(itemModel);
        return new RedirectView("/");
    }
}
