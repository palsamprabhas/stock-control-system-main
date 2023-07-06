package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.dto.request.ConsumerItemRequest;
import com.assignment.stockcontrolsystemapi.dto.response.ConsumerItemResponse;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.InventoryResponse;
import com.assignment.stockcontrolsystemapi.model.ConsumerItem;
import com.assignment.stockcontrolsystemapi.model.Inventory;
import com.assignment.stockcontrolsystemapi.model.Rack;
import com.assignment.stockcontrolsystemapi.repository.ConsumerItemRepository;
import com.assignment.stockcontrolsystemapi.repository.InventoryRepository;
import com.assignment.stockcontrolsystemapi.repository.RackRepository;
import com.assignment.stockcontrolsystemapi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsumerItemService {

    @Autowired
    private ConsumerItemRepository consumerItemRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private RackRepository rackRepository;
    @Value("${admin.user}")
    private String adminUserName;

    public List<ConsumerItemResponse> getAllConsumerItems() {
        return consumerItemRepository.findAll()
            .stream()
            .map(consumerItem ->
                ConsumerItemResponse.builder()
                    .id(consumerItem.getId())
                    .itemId(consumerItem.getItemId())
                    .count(consumerItem.getCount())
                    .status(consumerItem.getStatus())
                    .consumerId(consumerItem.getConsumerId())
                    .stockId(consumerItem.getStockId())
                    .totalPrice(consumerItem.getTotalPrice())
                    .item(itemService.getItemById(consumerItem.getItemId()))
                    .build())
            .collect(Collectors.toList());
    }

    public GenericResponse addConsumerItem(ConsumerItemRequest consumerItemRequest) {
        ConsumerItem consumerItem = ConsumerItem.builder()
            .itemId(consumerItemRequest.getItemId())
            .count(consumerItemRequest.getCount())
            .status(consumerItemRequest.getStatus())
            .consumerId(consumerItemRequest.getConsumerId())
            .stockId(consumerItemRequest.getStockId())
            .totalPrice(consumerItemRequest.getTotalPrice())
            .build();

        ConsumerItem response = consumerItemRepository.save(consumerItem);

        return new GenericResponse(Constants.SUCCESS, "consumer item added successfully");
    }

    public GenericResponse updateStockIdAndStatus(String consumerItemId, String stockId, String status) {
        Optional<ConsumerItem> consumerItemOptional = consumerItemRepository.findById(consumerItemId);

        if (consumerItemOptional.isPresent()) {
            ConsumerItem consumerItem = consumerItemOptional.get();
            consumerItem.setStockId(stockId);
            consumerItem.setStatus(status);

            consumerItemRepository.save(consumerItem);

            if("APPROVED".equals(status)) {
                inventoryService.updateInventoryCount(stockId, consumerItem.getCount());
            }

            if("RETURN".equals(status)) {
                Optional<Inventory> existingReturnInventoryOptional = inventoryRepository.findAll().stream()
                    .filter(inv -> "RETURN".equals(inv.getStatus()) && inv.getItemId().equals(consumerItem.getItemId()))
                    .findFirst();

                if(existingReturnInventoryOptional.isPresent()) {
                    Inventory existingReturnInventory = existingReturnInventoryOptional.get();
                    existingReturnInventory.setCount(existingReturnInventory.getCount() + consumerItem.getCount());
                    inventoryRepository.save(existingReturnInventory);

                    inventoryRepository.save(existingReturnInventory);
                } else {
                   Optional<Rack> optionalRack =  rackRepository.findAll().stream().filter(rack -> rack.isDamagedRack()).findFirst();
                   if(optionalRack.isPresent()) {
                       Inventory returnInventory = Inventory.builder()
                           .itemId(consumerItem.getItemId())
                           .rackId(optionalRack.get().getId())
                           .count(consumerItem.getCount())
                           .totalPrice(consumerItem.getTotalPrice())
                           .supplierId(adminUserName)
                           .status("RETURN")
                           .build();
                       inventoryRepository.save(returnInventory);
                   }
                }
            }

            return new GenericResponse(Constants.SUCCESS, "consumer item updated successfully");
        } else {
            return new GenericResponse(Constants.ERROR, "consumer item not found");
        }
    }


    public GenericResponse deleteConsumerItemById(String id) {
        consumerItemRepository.deleteById(id);
        return new GenericResponse(Constants.SUCCESS, "consumer item deleted successfully");
    }
}
