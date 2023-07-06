package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.dto.request.ConsumerItemRequest;
import com.assignment.stockcontrolsystemapi.dto.request.ItemRequest;
import com.assignment.stockcontrolsystemapi.dto.response.ConsumerItemResponse;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.UserResponse;
import com.assignment.stockcontrolsystemapi.model.ConsumerItem;
import com.assignment.stockcontrolsystemapi.service.ConsumerItemService;
import com.assignment.stockcontrolsystemapi.service.ConsumerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ConsumerItemControllerTest {

    @InjectMocks
    private ConsumerItemController consumerItemController;

    @Mock
    private ConsumerItemService consumerItemService;

    @Test
    void test_getAllConsumerItems() {
        ConsumerItemResponse consumerItemResponse = Mockito.mock(ConsumerItemResponse.class);

        Mockito.when(consumerItemService.getAllConsumerItems()).thenReturn(List.of(consumerItemResponse));

        List<ConsumerItemResponse> response = consumerItemController.getAllConsumerItems();

        assertEquals(response.size(), 1);
    }

    @Test
    void test_addConsumerItem() {
        ConsumerItemRequest consumerItemRequest = Mockito.mock(ConsumerItemRequest.class);
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(consumerItemService.addConsumerItem(consumerItemRequest)).thenReturn(genericResponse);

        GenericResponse response = consumerItemController.addConsumerItem(consumerItemRequest);

        assertEquals(response, genericResponse);
    }

    @Test
    void test_updateStockIdAndStatus() {
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(consumerItemService.updateStockIdAndStatus("itemId", "stockId", "APPROVED")).thenReturn(genericResponse);

        GenericResponse response = consumerItemController.updateStockIdAndStatus("itemId", "stockId", "APPROVED");

        assertEquals(response, genericResponse);
    }

    @Test
    void test_deleteConsumerItemById() {
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(consumerItemService.deleteConsumerItemById("testId")).thenReturn(genericResponse);

        GenericResponse response = consumerItemController.deleteConsumerItemById("testId");

        assertEquals(response, genericResponse);
    }
}