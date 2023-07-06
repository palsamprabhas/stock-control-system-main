package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.dto.response.ConsumerItemResponse;
import com.assignment.stockcontrolsystemapi.dto.response.InventoryResponse;
import com.assignment.stockcontrolsystemapi.dto.response.messenger.*;
import com.assignment.stockcontrolsystemapi.model.Item;
import com.assignment.stockcontrolsystemapi.model.User;
import com.assignment.stockcontrolsystemapi.repository.ItemRepository;
import com.assignment.stockcontrolsystemapi.repository.UserRepository;
import com.assignment.stockcontrolsystemapi.util.Constants;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2IntentMessage;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2IntentMessageText;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2WebhookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DialogflowService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private ConsumerItemService consumerItemService;

    public GoogleCloudDialogflowV2WebhookResponse getResponse(GoogleCloudDialogflowV2WebhookRequest request) {
        GoogleCloudDialogflowV2WebhookResponse response = null;

        switch (request.getQueryResult().getIntent().getDisplayName()) {
            case Constants.GET_ALL_ITEMS:
                response = getAllItemsResponse();
                break;
            case Constants.GET_ALL_CONSUMERS:
                response = getAllUserByRoleResponse(Constants.CONSUMER);
                break;
            case Constants.GET_ALL_SUPPLIERS:
                response = getAllUserByRoleResponse(Constants.SUPPLIER);
                break;
            case Constants.GET_ALL_INPROGRESS_INVENTORIES:
                response = getAllInventoriesByStatusResponse("IN_PROGRESS");
                break;
            case Constants.GET_ALL_ACCEPTED_INVENTORIES:
                response = getAllInventoriesByStatusResponse("SUPPLIER_ACCEPTED");
                break;
            case Constants.GET_ALL_COMPLETED_INVENTORIES:
                response = getAllInventoriesByStatusResponse("COMPLETED");
                break;
            case Constants.GET_ALL_INSTOCK_INVENTORIES:
                response = getAllInventoriesByStatusResponse("IN_STOCK");
                break;
            case Constants.GET_ALL_RETURNED_INVENTORIES:
                response = getAllInventoriesByStatusResponse("RETURN");
                break;
            case Constants.GET_ALL_PENDING_ITEM_REQUESTS:
                response = getAllItemRequestsByStatusResponse("IN_PROGRESS");
                break;
            case Constants.GET_ALL_APPROVED_ITEM_REQUESTS:
                response = getAllItemRequestsByStatusResponse("APPROVED");
                break;
            case Constants.GET_ALL_DELIVERED_ITEM_REQUESTS:
                response = getAllItemRequestsByStatusResponse("RECEIVED");
                break;
            case Constants.GET_ALL_RETURNED_ITEM_REQUESTS:
                response = getAllItemRequestsByStatusResponse("RETURN");
                break;
            default:

        }
        return response;
    }

    private GoogleCloudDialogflowV2WebhookResponse getAllItemsResponse() {
        List<Item> items = itemRepository.findAll();

        if(items.size() > 0) {
            List<PayloadElement> payloadElements = new ArrayList<>();
            for (Item item : items) {
                PayloadElement payloadElement = new PayloadElement();
                payloadElement.setTitle(item.getName());
                String subTitle = String.format(
                    "Description: %s\nPrice: %s\nExpiry Date: %s",
                    item.getDescription(),
                    item.getPrice(),
                    item.getExpiryDate()
                );
                payloadElement.setImage_url(item.getImageUrl());
                payloadElement.setSubtitle(subTitle);
                payloadElements.add(payloadElement);
            }

            Payload payload = new Payload();
            payload.setTemplate_type("generic");
            payload.setElements(payloadElements);

            Attachment attachment = new Attachment();
            attachment.setType("template");
            attachment.setPayload(payload);

            AttachmentResponse attachmentResponse = new AttachmentResponse();
            attachmentResponse.setAttachment(attachment);

            GoogleCloudDialogflowV2IntentMessage msg = new GoogleCloudDialogflowV2IntentMessage();
            GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
            Map facebookPayload = Map.of("facebook", attachmentResponse);
            msg.setPayload(facebookPayload);

            QuickReply quickReplyMainMenu = new QuickReply("MAIN_MENU", "main menu", "text");
            List<QuickReply> quickReplies = Arrays.asList(quickReplyMainMenu);
            QuickReplyResponse quickReplyResponse = new QuickReplyResponse("select an option", quickReplies);
            GoogleCloudDialogflowV2IntentMessage followupMessage = new GoogleCloudDialogflowV2IntentMessage();
            Map quickReplyPayload = Map.of("facebook", quickReplyResponse);
            followupMessage.setPayload(quickReplyPayload);

            response.setFulfillmentMessages(List.of(msg, followupMessage));

            return response;
        } else {
            GoogleCloudDialogflowV2IntentMessage msg = new GoogleCloudDialogflowV2IntentMessage();
            GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
            GoogleCloudDialogflowV2IntentMessageText googleCloudDialogflowV2IntentMessageText = new GoogleCloudDialogflowV2IntentMessageText();
            googleCloudDialogflowV2IntentMessageText.setText(List.of("Don't have items "));
            msg.setText(googleCloudDialogflowV2IntentMessageText);

            QuickReply quickReplyMainMenu = new QuickReply("MAIN_MENU", "main menu", "text");
            List<QuickReply> quickReplies = Arrays.asList(quickReplyMainMenu);
            QuickReplyResponse quickReplyResponse = new QuickReplyResponse("select an option", quickReplies);
            GoogleCloudDialogflowV2IntentMessage followupMessage = new GoogleCloudDialogflowV2IntentMessage();
            Map quickReplyPayload = Map.of("facebook", quickReplyResponse);
            followupMessage.setPayload(quickReplyPayload);

            response.setFulfillmentMessages(List.of(msg, followupMessage));

            return response;
        }
    }

    private GoogleCloudDialogflowV2WebhookResponse getAllUserByRoleResponse(String role) {
        List<User> consumers = userRepository.findAll()
            .stream()
            .filter(user -> role.equals(user.getRole()))
            .collect(Collectors.toList());

        if(consumers.size() > 0) {
            List<PayloadElement> payloadElements = new ArrayList<>();
            for (User consumer : consumers) {
                PayloadElement payloadElement = new PayloadElement();
                payloadElement.setTitle(consumer.getCompanyName() + "(Status: " + consumer.getStatus() + ")");
                String subTitle = String.format(
                    "Address: %s\nEmail: %s\nPhone: %s",
                    consumer.getAddress(),
                    consumer.getEmail(),
                    consumer.getPhoneNumber()
                );
                ;
                payloadElement.setSubtitle(subTitle);
                payloadElements.add(payloadElement);
            }

            Payload payload = new Payload();
            payload.setTemplate_type("generic");
            payload.setElements(payloadElements);

            Attachment attachment = new Attachment();
            attachment.setType("template");
            attachment.setPayload(payload);

            AttachmentResponse attachmentResponse = new AttachmentResponse();
            attachmentResponse.setAttachment(attachment);

            GoogleCloudDialogflowV2IntentMessage msg = new GoogleCloudDialogflowV2IntentMessage();
            GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
            Map facebookPayload = Map.of("facebook", attachmentResponse);
            msg.setPayload(facebookPayload);

            QuickReply quickReplyMainMenu = new QuickReply("MAIN_MENU", "main menu", "text");
            List<QuickReply> quickReplies = Arrays.asList(quickReplyMainMenu);
            QuickReplyResponse quickReplyResponse = new QuickReplyResponse("select an option", quickReplies);
            GoogleCloudDialogflowV2IntentMessage followupMessage = new GoogleCloudDialogflowV2IntentMessage();
            Map quickReplyPayload = Map.of("facebook", quickReplyResponse);
            followupMessage.setPayload(quickReplyPayload);

            response.setFulfillmentMessages(List.of(msg, followupMessage));

            return response;
        } else {
            GoogleCloudDialogflowV2IntentMessage msg = new GoogleCloudDialogflowV2IntentMessage();
            GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
            GoogleCloudDialogflowV2IntentMessageText googleCloudDialogflowV2IntentMessageText = new GoogleCloudDialogflowV2IntentMessageText();
            googleCloudDialogflowV2IntentMessageText.setText(List.of("Don't have users with role " + role.toLowerCase()));
            msg.setText(googleCloudDialogflowV2IntentMessageText);

            QuickReply quickReplyMainMenu = new QuickReply("MAIN_MENU", "main menu", "text");
            List<QuickReply> quickReplies = Arrays.asList(quickReplyMainMenu);
            QuickReplyResponse quickReplyResponse = new QuickReplyResponse("select an option", quickReplies);
            GoogleCloudDialogflowV2IntentMessage followupMessage = new GoogleCloudDialogflowV2IntentMessage();
            Map quickReplyPayload = Map.of("facebook", quickReplyResponse);
            followupMessage.setPayload(quickReplyPayload);

            response.setFulfillmentMessages(List.of(msg, followupMessage));

            return response;
        }
    }



    private GoogleCloudDialogflowV2WebhookResponse getAllInventoriesByStatusResponse(String status) {
        List<InventoryResponse> inProgressInventories = inventoryService.getAllInventories()
            .stream()
            .filter(inventory -> status.equals(inventory.getStatus()))
            .collect(Collectors.toList());

        if(inProgressInventories.size() > 0) {
        List<PayloadElement> payloadElements = new ArrayList<>();
        for (InventoryResponse inventory : inProgressInventories) {
            PayloadElement payloadElement = new PayloadElement();
            payloadElement.setTitle(inventory.getItem().getName() + " (Count: " + inventory.getCount() + ")");
            String subTitle = String.format(
                "Total Price: %s\nStatus: %s\nItem Price: %s",
                inventory.getTotalPrice(),
                inventory.getStatus(),
                inventory.getItem().getPrice()
            );
            payloadElement.setSubtitle(subTitle);
            payloadElement.setImage_url(inventory.getItem().getImageUrl());
            payloadElements.add(payloadElement);
        }

        Payload payload = new Payload();
        payload.setTemplate_type("generic");
        payload.setElements(payloadElements);

        Attachment attachment = new Attachment();
        attachment.setType("template");
        attachment.setPayload(payload);

        AttachmentResponse attachmentResponse = new AttachmentResponse();
        attachmentResponse.setAttachment(attachment);

        GoogleCloudDialogflowV2IntentMessage msg = new GoogleCloudDialogflowV2IntentMessage();
        GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
        Map facebookPayload = Map.of("facebook", attachmentResponse);
        msg.setPayload(facebookPayload);

        QuickReply quickReplyMainMenu = new QuickReply("MAIN_MENU", "main menu", "text");
        QuickReply quickReplyPreviousMenu = new QuickReply("GET_ALL_INVENTORIES", "previous menu", "text");
        List<QuickReply> quickReplies = Arrays.asList(quickReplyMainMenu, quickReplyPreviousMenu);
        QuickReplyResponse quickReplyResponse = new QuickReplyResponse("select an option", quickReplies);
        GoogleCloudDialogflowV2IntentMessage followupMessage = new GoogleCloudDialogflowV2IntentMessage();
        Map quickReplyPayload = Map.of("facebook", quickReplyResponse);
        followupMessage.setPayload(quickReplyPayload);

        response.setFulfillmentMessages(List.of(msg, followupMessage));

        return response;
        } else {
            GoogleCloudDialogflowV2IntentMessage msg = new GoogleCloudDialogflowV2IntentMessage();
            GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
            GoogleCloudDialogflowV2IntentMessageText googleCloudDialogflowV2IntentMessageText = new GoogleCloudDialogflowV2IntentMessageText();
            googleCloudDialogflowV2IntentMessageText.setText(List.of("Don't have any " + status.toLowerCase() + " inventories"));
            msg.setText(googleCloudDialogflowV2IntentMessageText);

            QuickReply quickReplyMainMenu = new QuickReply("MAIN_MENU", "main menu", "text");
            QuickReply quickReplyPreviousMenu = new QuickReply("GET_ALL_INVENTORIES", "previous menu", "text");
            List<QuickReply> quickReplies = Arrays.asList(quickReplyMainMenu, quickReplyPreviousMenu);
            QuickReplyResponse quickReplyResponse = new QuickReplyResponse("select an option", quickReplies);
            GoogleCloudDialogflowV2IntentMessage followupMessage = new GoogleCloudDialogflowV2IntentMessage();
            Map quickReplyPayload = Map.of("facebook", quickReplyResponse);
            followupMessage.setPayload(quickReplyPayload);

            response.setFulfillmentMessages(List.of(msg, followupMessage));

            return response;
        }
    }

    private GoogleCloudDialogflowV2WebhookResponse getAllItemRequestsByStatusResponse(String status) {
        List<ConsumerItemResponse> consumerItemResponses = consumerItemService.getAllConsumerItems()
            .stream()
            .filter(consumerItem -> status.equals(consumerItem.getStatus()))
            .collect(Collectors.toList());

        if(consumerItemResponses.size() > 0) {
            List<PayloadElement> payloadElements = new ArrayList<>();
            for (ConsumerItemResponse consumerItemResponse : consumerItemResponses) {
                PayloadElement payloadElement = new PayloadElement();
                payloadElement.setTitle(consumerItemResponse.getItem().getName() + " (Count: " + consumerItemResponse.getCount() + ")");
                String subTitle = String.format(
                    "Total Price: %s\nStatus: %s\nItem Price: %s",
                    consumerItemResponse.getTotalPrice(),
                    consumerItemResponse.getStatus(),
                    consumerItemResponse.getItem().getPrice()
                );
                payloadElement.setImage_url(consumerItemResponse.getItem().getImageUrl());
                payloadElement.setSubtitle(subTitle);
                payloadElements.add(payloadElement);
            }

            Payload payload = new Payload();
            payload.setTemplate_type("generic");
            payload.setElements(payloadElements);

            Attachment attachment = new Attachment();
            attachment.setType("template");
            attachment.setPayload(payload);

            AttachmentResponse attachmentResponse = new AttachmentResponse();
            attachmentResponse.setAttachment(attachment);

            GoogleCloudDialogflowV2IntentMessage msg = new GoogleCloudDialogflowV2IntentMessage();
            GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
            Map facebookPayload = Map.of("facebook", attachmentResponse);
            msg.setPayload(facebookPayload);

            QuickReply quickReplyMainMenu = new QuickReply("MAIN_MENU", "main menu", "text");
            QuickReply quickReplyPreviousMenu = new QuickReply("GET_ALL_ITEM_REQUESTS", "previous menu", "text");
            List<QuickReply> quickReplies = Arrays.asList(quickReplyMainMenu, quickReplyPreviousMenu);
            QuickReplyResponse quickReplyResponse = new QuickReplyResponse("select an option", quickReplies);
            GoogleCloudDialogflowV2IntentMessage followupMessage = new GoogleCloudDialogflowV2IntentMessage();
            Map quickReplyPayload = Map.of("facebook", quickReplyResponse);
            followupMessage.setPayload(quickReplyPayload);

            response.setFulfillmentMessages(List.of(msg, followupMessage));

            return response;
        } else {
            GoogleCloudDialogflowV2IntentMessage msg = new GoogleCloudDialogflowV2IntentMessage();
            GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
            GoogleCloudDialogflowV2IntentMessageText googleCloudDialogflowV2IntentMessageText = new GoogleCloudDialogflowV2IntentMessageText();
            googleCloudDialogflowV2IntentMessageText.setText(List.of("Don't have any " + status.toLowerCase() + " item requests"));
            msg.setText(googleCloudDialogflowV2IntentMessageText);

            QuickReply quickReplyMainMenu = new QuickReply("MAIN_MENU", "main menu", "text");
            QuickReply quickReplyPreviousMenu = new QuickReply("GET_ALL_ITEM_REQUESTS", "previous menu", "text");
            List<QuickReply> quickReplies = Arrays.asList(quickReplyMainMenu, quickReplyPreviousMenu);
            QuickReplyResponse quickReplyResponse = new QuickReplyResponse("select an option", quickReplies);
            GoogleCloudDialogflowV2IntentMessage followupMessage = new GoogleCloudDialogflowV2IntentMessage();
            Map quickReplyPayload = Map.of("facebook", quickReplyResponse);
            followupMessage.setPayload(quickReplyPayload);


            return response;
        }
    }

}
