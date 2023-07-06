package com.assignment.stockcontrolsystemapi.repository;

import com.assignment.stockcontrolsystemapi.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
}
