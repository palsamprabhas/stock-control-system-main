package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.dto.request.InventoryRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.InventoryResponse;
import com.assignment.stockcontrolsystemapi.dto.response.ItemResponse;
import com.assignment.stockcontrolsystemapi.dto.response.RackResponse;
import com.assignment.stockcontrolsystemapi.model.Inventory;
import com.assignment.stockcontrolsystemapi.model.Item;
import com.assignment.stockcontrolsystemapi.model.Rack;
import com.assignment.stockcontrolsystemapi.repository.InventoryRepository;
import com.assignment.stockcontrolsystemapi.repository.ItemRepository;
import com.assignment.stockcontrolsystemapi.repository.RackRepository;
import com.assignment.stockcontrolsystemapi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private RackRepository rackRepository;

    public List<InventoryResponse> getAllInventories() {
        return inventoryRepository.findAll()
            .stream()
            .map(inventory ->
                InventoryResponse.builder()
                    .id(inventory.getId())
                    .itemId(inventory.getItemId())
                    .count(inventory.getCount())
                    .totalPrice(inventory.getTotalPrice())
                    .status(inventory.getStatus())
                    .item(itemService.getItemById(inventory.getItemId()))
                    .supplierId(inventory.getSupplierId())
                    .rackId(inventory.getRackId())
                    .build())
            .collect(Collectors.toList());
    }

    public InventoryResponse getInventoryById(String id) {
        return inventoryRepository.findById(id).map(inventory ->
            InventoryResponse.builder()
                .id(inventory.getId())
                .itemId(inventory.getItemId())
                .count(inventory.getCount())
                .status(inventory.getStatus())
                .totalPrice(inventory.getTotalPrice())
                .item(itemService.getItemById(inventory.getItemId()))
                .supplierId(inventory.getSupplierId())
                .rackId(inventory.getRackId())
                .build()).get();
    }

    public List<InventoryResponse> getInventoriesByItemIdAndStatus(String itemId, String status) {
        return inventoryRepository.findAll()
            .stream()
            .filter(inventory -> inventory.getItemId().equals(itemId) && inventory.getStatus().equals(status))
            .map(inventory ->
                InventoryResponse.builder()
                    .id(inventory.getId())
                    .itemId(inventory.getItemId())
                    .count(inventory.getCount())
                    .status(inventory.getStatus())
                    .totalPrice(inventory.getTotalPrice())
                    .item(itemService.getItemById(inventory.getItemId()))
                    .supplierId(inventory.getSupplierId())
                    .rackId(inventory.getRackId())
                    .build()
            ).collect(Collectors.toList());
    }

    public GenericResponse addInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = Inventory.builder()
            .itemId(inventoryRequest.getItemId())
            .rackId(inventoryRequest.getRackId())
            .count(inventoryRequest.getCount())
            .totalPrice(inventoryRequest.getTotalPrice())
            .status(inventoryRequest.getStatus())
            .build();

        Inventory response = inventoryRepository.save(inventory);

        return new GenericResponse(Constants.SUCCESS, "inventory added successfully");
    }

    public GenericResponse updateInventory(InventoryRequest inventoryRequest) {
        Optional<Inventory> inventory = inventoryRepository.findById(inventoryRequest.getId());
        if (inventory.isPresent()) {
            Inventory updatedInventory = Inventory.builder()
                .id(inventoryRequest.getId())
                .itemId(inventoryRequest.getItemId())
                .count(inventoryRequest.getCount())
                .status(inventoryRequest.getStatus())
                .totalPrice(inventoryRequest.getTotalPrice())
                .supplierId(inventoryRequest.getSupplierId())
                .rackId(inventoryRequest.getRackId())
                .build();
            inventoryRepository.save(updatedInventory);
            return new GenericResponse(Constants.SUCCESS, "inventory updated successfully");
        } else {
            return new GenericResponse(Constants.ERROR, "inventory not found");
        }
    }

    public GenericResponse updateInventoryByStatusAndSupplierId(String id, String status, String supplierId) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);
        if (inventoryOptional.isPresent()) {
            Inventory inventory = inventoryOptional.get();
            Optional<Inventory> existingInStockInventoryOptional = inventoryRepository.findAll().stream()
                .filter(inv -> "IN_STOCK".equals(inv.getStatus()) && inv.getItemId().equals(inventory.getItemId()))
                .findFirst();
            if ("IN_STOCK".equals(status)) {
                if(existingInStockInventoryOptional.isPresent()) {
                    Inventory existingInstockInventory = existingInStockInventoryOptional.get();
                    existingInstockInventory.setCount(existingInstockInventory.getCount() + inventory.getCount());
                    inventoryRepository.save(existingInstockInventory);

                    inventory.setStatus("COMPLETED");
                    inventoryRepository.save(inventory);
                } else {
                    Inventory completedInventoryCopy = Inventory.builder()
                        .itemId(inventory.getItemId())
                        .rackId(inventory.getRackId())
                        .count(inventory.getCount())
                        .totalPrice(inventory.getTotalPrice())
                        .supplierId(inventory.getSupplierId())
                        .status("COMPLETED")
                        .build();
                    inventoryRepository.save(completedInventoryCopy);

                    inventory.setStatus(status);
                    inventoryRepository.save(inventory);
                }
            } else if("RETURN".equals(status)) {
                Optional<Rack> optionalRack =  rackRepository.findAll().stream().filter(rack-> rack.isDamagedRack()).findFirst();
                String rackId = inventory.getRackId();
                if(optionalRack.isPresent()) {
                    rackId = optionalRack.get().getId();
                }
                Inventory updatedInventory = Inventory.builder()
                    .id(inventory.getId())
                    .itemId(inventory.getItemId())
                    .count(inventory.getCount())
                    .status(status)
                    .totalPrice(inventory.getTotalPrice())
                    .supplierId(supplierId)
                    .rackId(rackId)
                    .build();
                inventoryRepository.save(updatedInventory);
            } else {
                Inventory updatedInventory = Inventory.builder()
                    .id(inventory.getId())
                    .itemId(inventory.getItemId())
                    .count(inventory.getCount())
                    .status(status)
                    .totalPrice(inventory.getTotalPrice())
                    .supplierId(supplierId)
                    .rackId(inventory.getRackId())
                    .build();
                inventoryRepository.save(updatedInventory);
            }
            return new GenericResponse(Constants.SUCCESS, "inventory updated successfully");
        } else {
            return new GenericResponse(Constants.ERROR, "inventory not found");
        }
    }

    public GenericResponse updateInventoryCount(String id, int count) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);
        if (inventoryOptional.isPresent()) {
            Inventory inventory = inventoryOptional.get();
            Inventory updatedInventory = Inventory.builder()
                .id(inventory.getId())
                .itemId(inventory.getItemId())
                .count(inventory.getCount() - count)
                .status(inventory.getStatus())
                .totalPrice(inventory.getTotalPrice())
                .supplierId(inventory.getSupplierId())
                .rackId(inventory.getRackId())
                .build();
            updatedInventory = inventoryRepository.save(updatedInventory);

            // Raise new request if inventory count less than 100
            if(updatedInventory.getCount() < 100) {
                ItemResponse item = itemService.getItemById(updatedInventory.getItemId());
                Inventory newInventoryRequest = Inventory.builder()
                    .itemId(updatedInventory.getItemId())
                    .count(100)
                    .totalPrice(item.getPrice() * 100)
                    .status("IN_PROGRESS")
                    .rackId(inventory.getRackId())
                    .build();
                inventoryRepository.save(newInventoryRequest);
            }

            return new GenericResponse(Constants.SUCCESS, "inventory updated successfully");
        } else {
            return new GenericResponse(Constants.ERROR, "inventory not found");
        }
    }

    public GenericResponse deleteInventoryById(String id) {
        inventoryRepository.deleteById(id);
        return new GenericResponse(Constants.SUCCESS, "inventory deleted successfully");
    }
}
