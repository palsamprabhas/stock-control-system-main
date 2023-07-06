package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.dto.request.UserRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.UserResponse;
import com.assignment.stockcontrolsystemapi.model.User;
import com.assignment.stockcontrolsystemapi.repository.UserRepository;
import com.assignment.stockcontrolsystemapi.util.Constants;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ConsumerServiceTest {
    @InjectMocks
    private ConsumerService consumerService;

    @Mock
    private UserRepository userRepository;

    @Test
    void test_getAllConsumers() {
        User consumer = new User();
        consumer.setRole(Constants.CONSUMER);

        Mockito.when(userRepository.findAll()).thenReturn(List.of(consumer));

        List<UserResponse> responses = consumerService.getAllConsumers();

        assertEquals(responses.size(), 1);
    }

    @Test
    void test_getConsumerById() {
        User consumer = Mockito.mock(User.class);

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(consumer));

        UserResponse response = consumerService.getConsumerById("test_id");

        assertNotNull(response);
    }

    @Test
    void test_updateConsumer() {
        User consumer = Mockito.mock(User.class);
        UserRequest consumerRequest = Mockito.mock(UserRequest.class);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(consumer);
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(consumer));

        GenericResponse response = consumerService.updateConsumer(consumerRequest);

        assertEquals(response.getDescription(), "consumer updated successfully");
    }

    @Test
    void test_updateConsumer_not_found() {
        UserRequest consumerRequest = Mockito.mock(UserRequest.class);

        GenericResponse response = consumerService.updateConsumer(consumerRequest);

        assertEquals(response.getDescription(), "consumer not found");
    }

    @Test
    void test_updateConsumerStatus() {
        User consumer = Mockito.mock(User.class);
        UserRequest consumerRequest = Mockito.mock(UserRequest.class);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(consumer);
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(consumer));

        GenericResponse response = consumerService.updateConsumerStatus("test_user", "APPROVED");

        assertEquals(response.getDescription(), "consumer updated successfully");
    }

    @Test
    void test_updateConsumerStatus_not_found() {
        UserRequest consumerRequest = Mockito.mock(UserRequest.class);

        GenericResponse response = consumerService.updateConsumerStatus("test_user", "APPROVED");

        assertEquals(response.getDescription(), "consumer not found");
    }

    @Test
    void test_deleteConsumerById() {

        Mockito.doNothing().when(userRepository).deleteById(Mockito.any());

        GenericResponse response = consumerService.deleteConsumerById("test_id");

        assertEquals(response.getDescription(), "consumer deleted successfully");
    }
}