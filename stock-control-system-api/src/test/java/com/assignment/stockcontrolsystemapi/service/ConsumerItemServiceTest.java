package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.dto.request.ConsumerItemRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.ConsumerItemResponse;
import com.assignment.stockcontrolsystemapi.dto.response.ItemResponse;
import com.assignment.stockcontrolsystemapi.model.ConsumerItem;
import com.assignment.stockcontrolsystemapi.repository.ConsumerItemRepository;
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
class ConsumerItemServiceTest {
    @InjectMocks
    private ConsumerItemService consumerItemService;

    @Mock
    private ConsumerItemRepository consumerItemRepository;
    @Mock
    private ItemService itemService;
    @Mock
    private InventoryService inventoryService;
    @Mock
    private InventoryRepository inventoryRepository;
    @Mock
    private RackRepository rackRepository;


    @Test
    void test_getAllConsumerItems() {
        ConsumerItem consumerItem = Mockito.mock(ConsumerItem.class);
        ItemResponse itemResponse = Mockito.mock(ItemResponse.class);

        Mockito.when(consumerItemRepository.findAll()).thenReturn(List.of(consumerItem));
        Mockito.when(itemService.getItemById(Mockito.any())).thenReturn(itemResponse);

        List<ConsumerItemResponse> responses = consumerItemService.getAllConsumerItems();

        assertEquals(responses.size(), 1);
    }

    @Test
    void test_addConsumerItem() {
        ConsumerItem consumerItem = Mockito.mock(ConsumerItem.class);
        ConsumerItemRequest consumerItemRequest = Mockito.mock(ConsumerItemRequest.class);

        Mockito.when(consumerItemRepository.save(Mockito.any())).thenReturn(consumerItem);

        GenericResponse response = consumerItemService.addConsumerItem(consumerItemRequest);

        assertEquals(response.getDescription(), "consumer item added successfully");
    }


    @Test
    void test_updateStockIdAndStatus() {
        ConsumerItem consumerItem = Mockito.mock(ConsumerItem.class);

        Mockito.when(consumerItemRepository.findById(Mockito.any())).thenReturn(Optional.of(consumerItem));
        Mockito.when(consumerItemRepository.save(Mockito.any())).thenReturn(consumerItem);

        GenericResponse response = consumerItemService.updateStockIdAndStatus("consumer_item_id", "stock_id", "APPROVED");

        assertEquals(response.getDescription(), "consumer item updated successfully");
    }

    @Test
    void test_updateStockIdAndStatus_not_found() {

        GenericResponse response = consumerItemService.updateStockIdAndStatus("consumer_item_id", "stock_id", "APPROVED");

        assertEquals(response.getDescription(), "consumer item not found");
    }

    @Test
    void test_deleteConsumerItemById() {

        Mockito.doNothing().when(consumerItemRepository).deleteById(Mockito.any());

        GenericResponse response = consumerItemService.deleteConsumerItemById("test_id");

        assertEquals(response.getDescription(), "consumer item deleted successfully");
    }
}