package com.assignment.stockcontrolsystemapi.repository;

import com.assignment.stockcontrolsystemapi.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
}
