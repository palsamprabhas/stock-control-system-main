package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.dto.request.InventoryRequest;
import com.assignment.stockcontrolsystemapi.dto.request.ItemRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.InventoryResponse;
import com.assignment.stockcontrolsystemapi.dto.response.ItemResponse;
import com.assignment.stockcontrolsystemapi.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InventoryControllerTest {

    @InjectMocks
    private InventoryController inventoryController;

    @Mock
    private InventoryService inventoryService;

    @Test
    void test_getAllInventories() {
        InventoryResponse inventoryResponse = Mockito.mock(InventoryResponse.class);

        Mockito.when(inventoryService.getAllInventories()).thenReturn(List.of(inventoryResponse));

        List<InventoryResponse> responses = inventoryController.getAllInventories();

        assertEquals(responses.size(), 1);
    }

    @Test
    void test_getInventoryById() {
        InventoryResponse inventoryResponse = Mockito.mock(InventoryResponse.class);

        Mockito.when(inventoryService.getInventoryById("inventoryId")).thenReturn(inventoryResponse);

        InventoryResponse response = inventoryController.getInventoryById("inventoryId");

        assertEquals(response, inventoryResponse);

    }

    @Test
    void test_getInventoriesByItemIdAndStatus() {
        InventoryResponse inventoryResponse = Mockito.mock(InventoryResponse.class);

        Mockito.when(inventoryService.getInventoriesByItemIdAndStatus("inventoryId", "APPROVED")).thenReturn(List.of(inventoryResponse));

        List<InventoryResponse> responses = inventoryController.getInventoriesByItemIdAndStatus("inventoryId", "APPROVED");

        assertEquals(responses.size(), 1);
    }

    @Test
    void test_addInventory() {
        InventoryRequest inventoryRequest = Mockito.mock(InventoryRequest.class);
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(inventoryService.addInventory(inventoryRequest)).thenReturn(genericResponse);

        GenericResponse response = inventoryController.addInventory(inventoryRequest);

        assertEquals(response, genericResponse);
    }

    @Test
    void test_updateInventory() {
        InventoryRequest inventoryRequest = Mockito.mock(InventoryRequest.class);
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(inventoryService.updateInventory(inventoryRequest)).thenReturn(genericResponse);

        GenericResponse response = inventoryController.updateInventory(inventoryRequest);

        assertEquals(response, genericResponse);
    }

    @Test
    void test_updateInventoryByStatusAndSupplierId() {
        InventoryRequest inventoryRequest = Mockito.mock(InventoryRequest.class);
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(inventoryService.updateInventoryByStatusAndSupplierId("inventoryId", "RECEIVED", "supplierId")).thenReturn(genericResponse);

        GenericResponse response = inventoryController.updateInventoryByStatusAndSupplierId("inventoryId", "RECEIVED", "supplierId");

        assertEquals(response, genericResponse);
    }

    @Test
    void test_deleteInventoryById() {
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(inventoryService.deleteInventoryById("testId")).thenReturn(genericResponse);

        GenericResponse response = inventoryController.deleteInventoryById("testId");

        assertEquals(response, genericResponse);
    }
}