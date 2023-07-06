package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.dto.request.ItemRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.ItemResponse;
import com.assignment.stockcontrolsystemapi.model.Item;
import com.assignment.stockcontrolsystemapi.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;


    @Test
    void test_getAllItems() {
        Item item = Mockito.mock(Item.class);

        Mockito.when(itemRepository.findAll()).thenReturn(List.of(item));

        List<ItemResponse> responses = itemService.getAllItems();

        assertEquals(responses.size(), 1);
    }

    @Test
    void test_getItemById() {
        Item item = Mockito.mock(Item.class);

        Mockito.when(itemRepository.findById(Mockito.any())).thenReturn(Optional.of(item));

        ItemResponse response = itemService.getItemById("test_id");

        assertNotNull(response);
    }

    @Test
    void test_addItem() {
        Item item = Mockito.mock(Item.class);
        ItemRequest itemRequest = Mockito.mock(ItemRequest.class);

        Mockito.when(itemRepository.save(Mockito.any())).thenReturn(item);

        GenericResponse response = itemService.addItem(itemRequest);

        assertEquals(response.getDescription(), "item added successfully");
    }

    @Test
    void test_updateItem() {
        Item item = Mockito.mock(Item.class);
        ItemRequest itemRequest = Mockito.mock(ItemRequest.class);

        Mockito.when(itemRepository.save(Mockito.any())).thenReturn(item);
        Mockito.when(itemRepository.findById(Mockito.any())).thenReturn(Optional.of(item));

        GenericResponse response = itemService.updateItem(itemRequest);

        assertEquals(response.getDescription(), "item updated successfully");
    }

    @Test
    void test_updateItem_not_found() {
        ItemRequest itemRequest = Mockito.mock(ItemRequest.class);

        GenericResponse response = itemService.updateItem(itemRequest);

        assertEquals(response.getDescription(), "item not found");
    }

    @Test
    void test_deleteItemById() {

        Mockito.doNothing().when(itemRepository).deleteById(Mockito.any());

        GenericResponse response = itemService.deleteItemById("test_id");

        assertEquals(response.getDescription(), "item deleted successfully");
    }
}