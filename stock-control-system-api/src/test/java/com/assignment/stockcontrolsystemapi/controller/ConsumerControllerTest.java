package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.dto.request.UserRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.UserResponse;
import com.assignment.stockcontrolsystemapi.service.ConsumerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConsumerControllerTest {

    @InjectMocks
    private ConsumerController consumerController;

    @Mock
    private ConsumerService consumerService;


    @Test
    void test_getAllConsumers() {
        UserResponse userResponse = Mockito.mock(UserResponse.class);

        Mockito.when(consumerService.getAllConsumers()).thenReturn(List.of(userResponse));

        List<UserResponse> response = consumerController.getAllConsumers();

        assertEquals(response.size(), 1);
    }

    @Test
    void test_getConsumerById() {
        UserResponse userResponse = Mockito.mock(UserResponse.class);

        Mockito.when(consumerService.getConsumerById("testId")).thenReturn(userResponse);

        UserResponse response = consumerController.getConsumerById("testId");

        assertEquals(response, userResponse);
    }

    @Test
    void test_updateConsumer() {
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(consumerService.updateConsumer(userRequest)).thenReturn(genericResponse);

        GenericResponse response = consumerController.updateConsumer(userRequest);

        assertEquals(response, genericResponse);
    }

    @Test
    void test_updateConsumerStatus() {
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(consumerService.updateConsumerStatus("testuser", "APPROVED")).thenReturn(genericResponse);

        GenericResponse response = consumerController.updateConsumerStatus("testuser", "APPROVED");

        assertEquals(response, genericResponse);
    }

    @Test
    void test_deleteConsumerById() {
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(consumerService.deleteConsumerById("testId")).thenReturn(genericResponse);

        GenericResponse response = consumerController.deleteConsumerById("testId");

        assertEquals(response, genericResponse);
    }
}