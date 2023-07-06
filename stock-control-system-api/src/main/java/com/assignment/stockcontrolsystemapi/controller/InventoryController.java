package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.dto.request.InventoryRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.InventoryResponse;
import com.assignment.stockcontrolsystemapi.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<InventoryResponse> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @GetMapping("/{id}")
    public InventoryResponse getInventoryById(@PathVariable String id) {
        return inventoryService.getInventoryById(id);
    }

    @GetMapping("/{itemId}/status/{status}")
    public List<InventoryResponse> getInventoriesByItemIdAndStatus(@PathVariable String itemId, @PathVariable String status) {
        return inventoryService.getInventoriesByItemIdAndStatus(itemId, status);
    }

    @PostMapping
    public GenericResponse addInventory(@RequestBody InventoryRequest inventoryRequest) {
        return inventoryService.addInventory(inventoryRequest);
    }

    @PutMapping
    public GenericResponse updateInventory(@RequestBody InventoryRequest inventoryRequest) {
        return inventoryService.updateInventory(inventoryRequest);
    }

    @PutMapping("/{id}/status/{status}/supplier/{supplierId}")
    public GenericResponse updateInventoryByStatusAndSupplierId(@PathVariable String id, @PathVariable String status, @PathVariable String supplierId) {
        return inventoryService.updateInventoryByStatusAndSupplierId(id, status, supplierId);
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteInventoryById(@PathVariable String id) {
        return inventoryService.deleteInventoryById(id);
    }

}
