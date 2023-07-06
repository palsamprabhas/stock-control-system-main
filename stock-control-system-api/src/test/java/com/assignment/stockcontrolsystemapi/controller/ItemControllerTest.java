package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.dto.request.ItemRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.ItemResponse;
import com.assignment.stockcontrolsystemapi.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ItemControllerTest {

    @InjectMocks
    ItemController itemController;

    @Mock
    private ItemService itemService;

    @Test
    void test_getAllItems() {
        ItemResponse itemResponse1 = Mockito.mock(ItemResponse.class);
        ItemResponse itemResponse2 = Mockito.mock(ItemResponse.class);
        ItemResponse itemResponse3 = Mockito.mock(ItemResponse.class);

        Mockito.when(itemService.getAllItems()).thenReturn(List.of(itemResponse1, itemResponse2, itemResponse3));

        List<ItemResponse> itemResponseList = itemController.getAllItems();

        assertEquals(itemResponseList.size(), 3);
    }

    @Test
    void test_getItemsById() {
        ItemResponse itemResponse = Mockito.mock(ItemResponse.class);

        Mockito.when(itemService.getItemById("testId")).thenReturn(itemResponse);

        ItemResponse response = itemController.getItemById("testId");

        assertEquals(response, itemResponse);
    }

    @Test
    void test_addItem() {
        ItemRequest itemRequest = Mockito.mock(ItemRequest.class);
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(itemService.addItem(itemRequest)).thenReturn(genericResponse);

        GenericResponse response = itemController.addItem(itemRequest);

        assertEquals(response, genericResponse);
    }

    @Test
    void test_updateItem() {
        ItemRequest itemRequest = Mockito.mock(ItemRequest.class);
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(itemService.updateItem(itemRequest)).thenReturn(genericResponse);

        GenericResponse response = itemController.updateItem(itemRequest);

        assertEquals(response, genericResponse);
    }

    @Test
    void test_deleteItemById() {
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(itemService.deleteItemById("testId")).thenReturn(genericResponse);

        GenericResponse response = itemController.deleteItemById("testId");

        assertEquals(response, genericResponse);
    }
}