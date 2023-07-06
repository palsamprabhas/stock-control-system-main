package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.dto.request.InventoryRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.InventoryResponse;
import com.assignment.stockcontrolsystemapi.dto.response.ItemResponse;
import com.assignment.stockcontrolsystemapi.model.Inventory;
import com.assignment.stockcontrolsystemapi.repository.InventoryRepository;
import com.assignment.stockcontrolsystemapi.repository.RackRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InventoryServiceTest {
    @InjectMocks
    private InventoryService inventoryService;

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private ItemService itemService;

    @Mock
    private RackRepository rackRepository;

    @Test
    void test_getAllInventorys() {
        Inventory inventory = Mockito.mock(Inventory.class);
        ItemResponse itemResponse = Mockito.mock(ItemResponse.class);

        Mockito.when(inventoryRepository.findAll()).thenReturn(List.of(inventory));
        Mockito.when(itemService.getItemById(Mockito.any())).thenReturn(itemResponse);

        List<InventoryResponse> responses = inventoryService.getAllInventories();

        assertEquals(responses.size(), 1);
    }

    @Test
    void test_getInventoryById() {
        Inventory inventory = Mockito.mock(Inventory.class);
        ItemResponse itemResponse = Mockito.mock(ItemResponse.class);

        Mockito.when(inventoryRepository.findById(Mockito.any())).thenReturn(Optional.of(inventory));
        Mockito.when(itemService.getItemById(Mockito.any())).thenReturn(itemResponse);
        Mockito.when(itemService.getItemById(Mockito.any())).thenReturn(itemResponse);

        InventoryResponse response = inventoryService.getInventoryById("test_id");

        assertNotNull(response);
    }

    @Test
    void test_getInventoriesByItemIdAndStatus() {
        Inventory inventory = new Inventory();
        inventory.setStatus("IN_STOCK");
        inventory.setItemId("test_id");
        ItemResponse itemResponse = Mockito.mock(ItemResponse.class);

        Mockito.when(inventoryRepository.findAll()).thenReturn(List.of(inventory));
        Mockito.when(itemService.getItemById(Mockito.any())).thenReturn(itemResponse);

        List<InventoryResponse> response = inventoryService.getInventoriesByItemIdAndStatus("test_id", "IN_STOCK");

        assertNotNull(response);
    }

    @Test
    void test_addInventory() {
        Inventory inventory = Mockito.mock(Inventory.class);
        InventoryRequest inventoryRequest = Mockito.mock(InventoryRequest.class);

        Mockito.when(inventoryRepository.save(Mockito.any())).thenReturn(inventory);

        GenericResponse response = inventoryService.addInventory(inventoryRequest);

        assertEquals(response.getDescription(), "inventory added successfully");
    }

    @Test
    void test_updateInventory() {
        Inventory inventory = Mockito.mock(Inventory.class);
        InventoryRequest inventoryRequest = Mockito.mock(InventoryRequest.class);

        Mockito.when(inventoryRepository.save(Mockito.any())).thenReturn(inventory);
        Mockito.when(inventoryRepository.findById(Mockito.any())).thenReturn(Optional.of(inventory));

        GenericResponse response = inventoryService.updateInventory(inventoryRequest);

        assertEquals(response.getDescription(), "inventory updated successfully");
    }

    @Test
    void test_updateInventory_not_found() {
        InventoryRequest inventoryRequest = Mockito.mock(InventoryRequest.class);

        GenericResponse response = inventoryService.updateInventory(inventoryRequest);

        assertEquals(response.getDescription(), "inventory not found");
    }

    @Test
    void test_updateInventoryCount() {
        Inventory inventory = new Inventory();
        inventory.setCount(10000);
        Mockito.when(inventoryRepository.save(Mockito.any())).thenReturn(inventory);
        Mockito.when(inventoryRepository.findById(Mockito.any())).thenReturn(Optional.of(inventory));

        GenericResponse response = inventoryService.updateInventoryCount("test_id", 100);

        assertEquals(response.getDescription(), "inventory updated successfully");
    }

    @Test
    void test_updateInventoryCount_not_found() {

        GenericResponse response = inventoryService.updateInventoryCount("test_id", 100);

        assertEquals(response.getDescription(), "inventory not found");
    }

    @Test
    void test_deleteInventoryById() {

        Mockito.doNothing().when(inventoryRepository).deleteById(Mockito.any());

        GenericResponse response = inventoryService.deleteInventoryById("test_id");

        assertEquals(response.getDescription(), "inventory deleted successfully");
    }
}