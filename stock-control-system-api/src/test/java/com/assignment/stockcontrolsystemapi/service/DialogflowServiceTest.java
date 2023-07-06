package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.dto.response.ConsumerItemResponse;
import com.assignment.stockcontrolsystemapi.dto.response.InventoryResponse;
import com.assignment.stockcontrolsystemapi.dto.response.ItemResponse;
import com.assignment.stockcontrolsystemapi.model.Item;
import com.assignment.stockcontrolsystemapi.model.User;
import com.assignment.stockcontrolsystemapi.repository.ItemRepository;
import com.assignment.stockcontrolsystemapi.repository.UserRepository;
import com.assignment.stockcontrolsystemapi.util.Constants;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2Intent;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2QueryResult;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2WebhookResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DialogflowServiceTest {

    @InjectMocks
    private DialogflowService dialogflowService;

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private InventoryService inventoryService;
    @Mock
    private ConsumerItemService consumerItemService;

    @Test
    public void test_getAllItemsResponse() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_ITEMS);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);

        Item item = Mockito.mock(Item.class);

        Mockito.when(itemRepository.findAll()).thenReturn(List.of(item));

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);

    }

    @Test
    public void test_getAllItemsResponse_items_not_found() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_ITEMS);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);


        Mockito.when(itemRepository.findAll()).thenReturn(List.of());

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);

    }

    @Test
    public void test_getAllConsumersResponse() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_CONSUMERS);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);

        User user = new User();
        user.setRole("CONSUMER");

        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);

    }

    @Test
    public void test_getAllConsumersResponse_items_not_found() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_CONSUMERS);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);

        Mockito.when(userRepository.findAll()).thenReturn(List.of());

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);

    }

    @Test
    public void test_getAllSuppliersResponse() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_SUPPLIERS);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);

        User user = new User();
        user.setRole("SUPPLIER");

        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);

    }

    @Test
    public void test_getAllSuppliersResponse_items_not_found() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_SUPPLIERS);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);

        Mockito.when(userRepository.findAll()).thenReturn(List.of());

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);

    }

    @Test
    public void test_getAllInProgressInventoriesResponse() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_INPROGRESS_INVENTORIES);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);

        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setStatus("IN_PROGRESS");
        inventoryResponse.setItem(new ItemResponse());

        Mockito.when(inventoryService.getAllInventories()).thenReturn(List.of(inventoryResponse));

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);

    }

    @Test
    public void test_getAllInProgressInventoriesResponse_items_not_found() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_INPROGRESS_INVENTORIES);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);

        Mockito.when(inventoryService.getAllInventories()).thenReturn(List.of());

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);
    }

    @Test
    public void test_getAllAcceptedInventoriesResponse() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_ACCEPTED_INVENTORIES);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);

        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setStatus("SUPPLIER_ACCEPTED");
        inventoryResponse.setItem(new ItemResponse());

        Mockito.when(inventoryService.getAllInventories()).thenReturn(List.of(inventoryResponse));

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);

    }

    @Test
    public void test_getAllCompletedInventoriesResponse() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_COMPLETED_INVENTORIES);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);

        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setStatus("COMPLETED");
        inventoryResponse.setItem(new ItemResponse());

        Mockito.when(inventoryService.getAllInventories()).thenReturn(List.of(inventoryResponse));

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);

    }

    @Test
    public void test_getAllInStockInventoriesResponse() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_INSTOCK_INVENTORIES);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);

        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setStatus("IN_STOCK");
        inventoryResponse.setItem(new ItemResponse());

        Mockito.when(inventoryService.getAllInventories()).thenReturn(List.of(inventoryResponse));

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);

    }

    @Test
    public void test_getAllReturnInventoriesResponse() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_RETURNED_INVENTORIES);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);

        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setStatus("RETURN");
        inventoryResponse.setItem(new ItemResponse());

        Mockito.when(inventoryService.getAllInventories()).thenReturn(List.of(inventoryResponse));

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);

    }

    @Test
    public void test_getAllPendingItemRequestsResponse() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_PENDING_ITEM_REQUESTS);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);

        ConsumerItemResponse consumerItemResponse = new ConsumerItemResponse();
        consumerItemResponse.setStatus("IN_PROGRESS");
        consumerItemResponse.setItem(new ItemResponse());

        Mockito.when(consumerItemService.getAllConsumerItems()).thenReturn(List.of(consumerItemResponse));

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);

    }

    @Test
    public void test_getAllPendingItemRequestsResponse_not_found() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_PENDING_ITEM_REQUESTS);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);


        Mockito.when(consumerItemService.getAllConsumerItems()).thenReturn(List.of());

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);

    }

    @Test
    public void test_getAllApprovedItemRequestsResponse() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_APPROVED_ITEM_REQUESTS);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);

        ConsumerItemResponse consumerItemResponse = new ConsumerItemResponse();
        consumerItemResponse.setStatus("APPROVED");
        consumerItemResponse.setItem(new ItemResponse());

        Mockito.when(consumerItemService.getAllConsumerItems()).thenReturn(List.of(consumerItemResponse));

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);

    }

    @Test
    public void test_getAllReceivedItemRequestsResponse() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_DELIVERED_ITEM_REQUESTS);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);

        ConsumerItemResponse consumerItemResponse = new ConsumerItemResponse();
        consumerItemResponse.setStatus("RECEIVED");
        consumerItemResponse.setItem(new ItemResponse());

        Mockito.when(consumerItemService.getAllConsumerItems()).thenReturn(List.of(consumerItemResponse));

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);

    }

    @Test
    public void test_getAllReturnItemRequestsResponse() {
        GoogleCloudDialogflowV2WebhookRequest request = new GoogleCloudDialogflowV2WebhookRequest();
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        GoogleCloudDialogflowV2Intent intent = new GoogleCloudDialogflowV2Intent();
        intent.setDisplayName(Constants.GET_ALL_RETURNED_ITEM_REQUESTS);
        queryResult.setIntent(intent);
        request.setQueryResult(queryResult);

        ConsumerItemResponse consumerItemResponse = new ConsumerItemResponse();
        consumerItemResponse.setStatus("RETURN");
        consumerItemResponse.setItem(new ItemResponse());

        Mockito.when(consumerItemService.getAllConsumerItems()).thenReturn(List.of(consumerItemResponse));

        GoogleCloudDialogflowV2WebhookResponse response
            = dialogflowService.getResponse(request);

        assertNotNull(response);

    }
}