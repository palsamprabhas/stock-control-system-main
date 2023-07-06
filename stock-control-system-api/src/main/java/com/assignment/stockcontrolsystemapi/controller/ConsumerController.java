package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.dto.request.UserRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.UserResponse;
import com.assignment.stockcontrolsystemapi.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping
    public List<UserResponse> getAllConsumers() {
        return consumerService.getAllConsumers();
    }

    @GetMapping("/{username}")
    public UserResponse getConsumerById(@PathVariable String username) {
        return consumerService.getConsumerById(username);
    }


    @PutMapping
    public GenericResponse updateConsumer(@RequestBody UserRequest userRequest) {
        return consumerService.updateConsumer(userRequest);
    }

    @PutMapping("/{username}/{status}")
    public GenericResponse updateConsumerStatus(@PathVariable String username, @PathVariable String status) {
        return consumerService.updateConsumerStatus(username, status);
    }

    @DeleteMapping("/{username}")
    public GenericResponse deleteConsumerById(@PathVariable String username) {
        return consumerService.deleteConsumerById(username);
    }

}
