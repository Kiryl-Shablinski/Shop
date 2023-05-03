package com.example.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/allItems")
public class AllItemsController {
    @GetMapping
    public String getAllItemsPage(){
        return "allItems";
    }
}
