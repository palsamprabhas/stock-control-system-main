package com.assignment.stockcontrolsystemapi.repository;

import com.assignment.stockcontrolsystemapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
