package com.example.shop.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "item_model")
public class ItemModel {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "disc")
    private String disc;

    @Column(name = "url")
    private String url;

    @Column(name = "add_date")
    private long time;

    @Column(name = "type")
    private String type;

    private String timeFormat;
}
