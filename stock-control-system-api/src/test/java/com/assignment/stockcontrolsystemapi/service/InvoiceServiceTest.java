package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.model.ConsumerItem;
import com.assignment.stockcontrolsystemapi.model.Inventory;
import com.assignment.stockcontrolsystemapi.model.Item;
import com.assignment.stockcontrolsystemapi.model.User;
import com.assignment.stockcontrolsystemapi.repository.ConsumerItemRepository;
import com.assignment.stockcontrolsystemapi.repository.InventoryRepository;
import com.assignment.stockcontrolsystemapi.repository.ItemRepository;
import com.assignment.stockcontrolsystemapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvoiceServiceTest {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private InventoryRepository inventoryRepository;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ConsumerItemRepository consumerItemRepository;

    @Test
    void test_generateSupplierInvoice() throws IOException {
        HttpServletResponse response = new MockHttpServletResponse();

        Inventory inventory = Mockito.mock(Inventory.class);
        User user = Mockito.mock(User.class);
        Item item = Mockito.mock(Item.class);

        Mockito.when(inventoryRepository.findById(Mockito.any())).thenReturn(Optional.of(inventory));
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(itemRepository.findById(Mockito.any())).thenReturn(Optional.of(item));

        invoiceService.generateSupplierInvoice("stock_id", response);
    }

    @Test
    void test_generateConsumerInvoice() throws IOException {
        HttpServletResponse response = new MockHttpServletResponse();

        ConsumerItem consumerItem = Mockito.mock(ConsumerItem.class);
        User user = Mockito.mock(User.class);
        Item item = Mockito.mock(Item.class);

        Mockito.when(consumerItemRepository.findById(Mockito.any())).thenReturn(Optional.of(consumerItem));
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(itemRepository.findById(Mockito.any())).thenReturn(Optional.of(item));

        invoiceService.generateConsumerInvoice("consumer_item_id", response);
    }
}