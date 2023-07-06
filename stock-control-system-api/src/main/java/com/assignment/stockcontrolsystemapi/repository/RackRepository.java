package com.assignment.stockcontrolsystemapi.repository;

import com.assignment.stockcontrolsystemapi.model.Rack;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RackRepository extends MongoRepository<Rack, String> {
}
