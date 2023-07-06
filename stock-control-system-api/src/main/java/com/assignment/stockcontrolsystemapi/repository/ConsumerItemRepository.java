package com.assignment.stockcontrolsystemapi.repository;

import com.assignment.stockcontrolsystemapi.model.ConsumerItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsumerItemRepository extends MongoRepository<ConsumerItem, String> {
}
