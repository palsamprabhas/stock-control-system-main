package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.dto.request.RackRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.InventoryResponse;
import com.assignment.stockcontrolsystemapi.dto.response.RackResponse;
import com.assignment.stockcontrolsystemapi.model.Inventory;
import com.assignment.stockcontrolsystemapi.model.Rack;
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
class RackServiceTest {
    @InjectMocks
    private RackService rackService;

    @Mock
    private RackRepository rackRepository;
    @Mock
    private InventoryService inventoryService;


    @Test
    void test_getAllRacks() {
        Rack rack = new Rack();
        rack.setId("1");
        InventoryResponse inventoryResponse = Mockito.mock(InventoryResponse.class);

        Mockito.when(rackRepository.findAll()).thenReturn(List.of(rack));
        Mockito.when(inventoryService.getAllInventories()).thenReturn(List.of(inventoryResponse));

        List<RackResponse> responses = rackService.getAllRacks();

        assertEquals(responses.size(), 1);
    }

    @Test
    void test_getRackById() {
        Rack rack = new Rack();
        rack.setId("1");
        InventoryResponse inventoryResponse = Mockito.mock(InventoryResponse.class);

        Mockito.when(rackRepository.findById(Mockito.any())).thenReturn(Optional.of(rack));
        Mockito.when(inventoryService.getAllInventories()).thenReturn(List.of(inventoryResponse));

        RackResponse response = rackService.getRackById("test_id");

        assertNotNull(response);
    }

    @Test
    void test_addRack() {
        Rack rack = Mockito.mock(Rack.class);
        RackRequest rackRequest = Mockito.mock(RackRequest.class);

        Mockito.when(rackRepository.save(Mockito.any())).thenReturn(rack);

        GenericResponse response = rackService.addRack(rackRequest);

        assertEquals(response.getDescription(), "rack added successfully");
    }

    @Test
    void test_updateRack() {
        Rack rack = Mockito.mock(Rack.class);
        RackRequest rackRequest = Mockito.mock(RackRequest.class);

        Mockito.when(rackRepository.save(Mockito.any())).thenReturn(rack);
        Mockito.when(rackRepository.findById(Mockito.any())).thenReturn(Optional.of(rack));

        GenericResponse response = rackService.updateRack(rackRequest);

        assertEquals(response.getDescription(), "rack updated successfully");
    }

    @Test
    void test_updateRack_not_found() {
        RackRequest rackRequest = Mockito.mock(RackRequest.class);

        GenericResponse response = rackService.updateRack(rackRequest);

        assertEquals(response.getDescription(), "rack not found");
    }

    @Test
    void test_deleteRackById() {

        Mockito.doNothing().when(rackRepository).deleteById(Mockito.any());

        GenericResponse response = rackService.deleteRackById("test_id");

        assertEquals(response.getDescription(), "rack deleted successfully");
    }
}