package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.service.DialogflowService;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2WebhookResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DialogflowWebhookControllerTest {

    @InjectMocks
    private DialogflowWebhookController dialogflowWebhookController;

    @Mock
    private DialogflowService dialogflowService;

    @Test
    void test_dialogflowWebhook() throws Exception {
        GoogleCloudDialogflowV2WebhookResponse googleCloudDialogflowV2WebhookResponse = new GoogleCloudDialogflowV2WebhookResponse();

        Mockito.when(dialogflowService.getResponse(Mockito.any())).thenReturn(googleCloudDialogflowV2WebhookResponse);

        GoogleCloudDialogflowV2WebhookResponse response = dialogflowWebhookController.dialogflowWebhook("{\"facebook\": \"test\"}");

        assertEquals(response, googleCloudDialogflowV2WebhookResponse);
    }
}