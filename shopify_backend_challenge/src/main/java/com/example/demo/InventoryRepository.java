package com.example.demo;

import com.example.demo.Model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
    Optional<Inventory> findInventoryByAmountAndName(int amount, String name);
}
