package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.dto.request.ConsumerItemRequest;
import com.assignment.stockcontrolsystemapi.dto.request.ItemRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.ConsumerItemResponse;
import com.assignment.stockcontrolsystemapi.service.ConsumerItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumeritem")
public class ConsumerItemController {

    @Autowired
    private ConsumerItemService consumerItemService;

    @GetMapping
    public List<ConsumerItemResponse> getAllConsumerItems() {
        return consumerItemService.getAllConsumerItems();
    }

    @PostMapping
    public GenericResponse addConsumerItem(@RequestBody ConsumerItemRequest consumerItemRequest) {
        return consumerItemService.addConsumerItem(consumerItemRequest);
    }

    @PutMapping("/{consumerItemId}/stock/{stockId}/status/{status}")
    public GenericResponse updateStockIdAndStatus(@PathVariable String consumerItemId,@PathVariable String stockId, @PathVariable String status) {
        return consumerItemService.updateStockIdAndStatus(consumerItemId, stockId,status);
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteConsumerItemById(@PathVariable String id) {
        return consumerItemService.deleteConsumerItemById(id);
    }

}
