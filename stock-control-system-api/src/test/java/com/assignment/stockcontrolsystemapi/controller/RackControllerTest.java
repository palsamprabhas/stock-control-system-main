package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.dto.request.RackRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.RackResponse;
import com.assignment.stockcontrolsystemapi.service.RackService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RackControllerTest {

    @InjectMocks
    private RackController rackController;

    @Mock
    private RackService rackService;

    @Test
    void getAllRacks() {
        RackResponse rackResponse = Mockito.mock(RackResponse.class);

        Mockito.when(rackService.getAllRacks()).thenReturn(List.of(rackResponse));

        List<RackResponse> response = rackController.getAllRacks();

        assertEquals(response.size(), 1);
    }

    @Test
    void getRackById() {
        RackResponse rackResponse = Mockito.mock(RackResponse.class);

        Mockito.when(rackService.getRackById("testId")).thenReturn(rackResponse);

        RackResponse response = rackController.getRackById("testId");

        assertEquals(response, rackResponse);
    }


    @Test
    void addRack() {
        RackRequest rackRequest = Mockito.mock(RackRequest.class);
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(rackService.addRack(rackRequest)).thenReturn(genericResponse);

        GenericResponse response = rackController.addRack(rackRequest);

        assertEquals(response, genericResponse);
    }

    @Test
    void updateRack() {
        RackRequest rackRequest = Mockito.mock(RackRequest.class);
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(rackService.updateRack(rackRequest)).thenReturn(genericResponse);

        GenericResponse response = rackController.updateRack(rackRequest);

        assertEquals(response, genericResponse);
    }

    @Test
    void deleteRackById() {
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(rackService.deleteRackById("testId")).thenReturn(genericResponse);

        GenericResponse response = rackController.deleteRackById("testId");

        assertEquals(response, genericResponse);
    }
}