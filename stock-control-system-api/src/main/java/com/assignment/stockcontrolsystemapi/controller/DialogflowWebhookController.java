package com.assignment.stockcontrolsystemapi.controller;


import com.assignment.stockcontrolsystemapi.dto.response.messenger.QuickReply;
import com.assignment.stockcontrolsystemapi.dto.response.messenger.QuickReplyResponse;
import com.assignment.stockcontrolsystemapi.service.DialogflowService;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2IntentMessage;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2WebhookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class DialogflowWebhookController {

    @Autowired
    private DialogflowService dialogflowService;

    @PostMapping(value = "/dialogflow-webhook", produces = {MediaType.APPLICATION_JSON_VALUE})
    public GoogleCloudDialogflowV2WebhookResponse dialogflowWebhook(@RequestBody String request) throws Exception {
        GoogleCloudDialogflowV2WebhookRequest googleCloudDialogflowV2WebhookRequest = GsonFactory.getDefaultInstance().createJsonObjectParser()
            .getJsonFactory()
            .fromString(request, GoogleCloudDialogflowV2WebhookRequest.class);

        return dialogflowService.getResponse(googleCloudDialogflowV2WebhookRequest);
    }
}
