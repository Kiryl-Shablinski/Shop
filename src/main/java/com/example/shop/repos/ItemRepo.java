package com.example.shop.repos;

import com.example.shop.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<ItemModel,Long> {
}
