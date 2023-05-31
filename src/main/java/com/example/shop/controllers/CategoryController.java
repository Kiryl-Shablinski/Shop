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
@RequestMapping("/category")
public class CategoryController {
  private final  ItemRepo itemRepo;

   private final FireBaseService fireBaseService;

    public CategoryController(ItemRepo itemRepo, FireBaseService fireBaseService) {
        this.itemRepo = itemRepo;
        this.fireBaseService = fireBaseService;
    }

    @GetMapping("/toys")
    public String getToys(Model model){
        List<ItemModel> list = itemRepo.findAllByType("игрушки");
        model.addAttribute("items", list);
        list.stream().forEach(itemModel -> itemModel.setUrl(fireBaseService.getUrl(itemModel.getUrl())));
        list = TimeHelper.getTime(list);
        return "allItems";
    }

    @GetMapping("/forPc")
    public String getForPc(Model model){
        List<ItemModel> list = itemRepo.findAllByType("для компьютера");
        model.addAttribute("items", list);
        list.stream().forEach(itemModel -> itemModel.setUrl(fireBaseService.getUrl(itemModel.getUrl())));
        list = TimeHelper.getTime(list);
        return "allItems";
    }

    @GetMapping("/forYou")
    public String getForYou(Model model){
        List<ItemModel> list = itemRepo.findAllByType("для себя");
        model.addAttribute("items", list);
        list.stream().forEach(itemModel -> itemModel.setUrl(fireBaseService.getUrl(itemModel.getUrl())));
        list = TimeHelper.getTime(list);
        return "allItems";
    }
}
