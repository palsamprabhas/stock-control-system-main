package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.dto.request.ItemRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.ItemResponse;
import com.assignment.stockcontrolsystemapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<ItemResponse> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public ItemResponse getItemById(@PathVariable String id) {
        return itemService.getItemById(id);
    }

    @PostMapping
    public GenericResponse addItem(@RequestBody ItemRequest itemRequest) {
        return itemService.addItem(itemRequest);
    }

    @PutMapping
    public GenericResponse updateItem(@RequestBody ItemRequest itemRequest) {
        return itemService.updateItem(itemRequest);
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteItemById(@PathVariable String id) {
        return itemService.deleteItemById(id);
    }

}
