package com.example.shop.repos;

import com.example.shop.models.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepo extends JpaRepository<RequestModel, Long> {

}
