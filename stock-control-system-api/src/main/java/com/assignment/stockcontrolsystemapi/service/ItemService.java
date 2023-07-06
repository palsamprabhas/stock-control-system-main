package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.dto.request.ItemRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.ItemResponse;
import com.assignment.stockcontrolsystemapi.model.Item;
import com.assignment.stockcontrolsystemapi.repository.ItemRepository;
import com.assignment.stockcontrolsystemapi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<ItemResponse> getAllItems() {
        return itemRepository.findAll()
            .stream()
            .map(item ->
                ItemResponse.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .description(item.getDescription())
                    .imageUrl(item.getImageUrl())
                    .price(item.getPrice())
                    .expiryDate(item.getExpiryDate())
                    .build())
            .collect(Collectors.toList());
    }

    public ItemResponse getItemById(String id) {
        return itemRepository.findById(id)
            .map(item ->
                ItemResponse.builder()
                    .id (item.getId())
                    .name(item.getName())
                    .description(item.getDescription())
                    .imageUrl(item.getImageUrl())
                    .price(item.getPrice())
                    .expiryDate(item.getExpiryDate())
                    .build())
            .get();
    }

    public GenericResponse addItem(ItemRequest itemRequest) {
        Item item = Item.builder()
            .name(itemRequest.getName())
            .description(itemRequest.getDescription())
            .imageUrl(itemRequest.getImageUrl())
            .price(itemRequest.getPrice())
            .expiryDate(itemRequest.getExpiryDate())
            .build();

        Item response = itemRepository.save(item);

        return new GenericResponse(Constants.SUCCESS, "item added successfully");
    }

    public GenericResponse updateItem(ItemRequest itemRequest) {
        Optional<Item> item = itemRepository.findById(itemRequest.getId());
        if (item.isPresent()) {
            Item updatedItem = Item.builder()
                .id(item.get().getId())
                .name(itemRequest.getName())
                .description(itemRequest.getDescription())
                .imageUrl(itemRequest.getImageUrl())
                .price(itemRequest.getPrice())
                .expiryDate(itemRequest.getExpiryDate())
                .build();
            itemRepository.save(updatedItem);
            return new GenericResponse(Constants.SUCCESS, "item updated successfully");
        } else {
            return new GenericResponse(Constants.ERROR, "item not found");
        }
    }

    public GenericResponse deleteItemById(String id) {
        itemRepository.deleteById(id);
        return new GenericResponse(Constants.SUCCESS, "item deleted successfully");
    }
}
