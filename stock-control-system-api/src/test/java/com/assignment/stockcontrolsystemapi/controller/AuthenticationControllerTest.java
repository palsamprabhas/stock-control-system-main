package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.dto.request.UserRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.UserResponse;
import com.assignment.stockcontrolsystemapi.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    @Test
    void test_register() {
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        UserResponse userResponse = Mockito.mock(UserResponse.class);

        Mockito.when(authenticationService.register(userRequest)).thenReturn(userResponse);

        UserResponse response = authenticationController.register(userRequest);

        assertEquals(response, userResponse);
    }

    @Test
    void test_login() throws Exception {
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        UserResponse userResponse = Mockito.mock(UserResponse.class);

        Mockito.when(authenticationService.login(userRequest)).thenReturn(userResponse);

        ResponseEntity<UserResponse> response = authenticationController.login(userRequest);

        assertEquals(response.getBody(), userResponse);
    }

    @Test
    void test_login_with_exception() throws Exception {
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        UserResponse userResponse = Mockito.mock(UserResponse.class);

        Mockito.when(authenticationService.login(userRequest)).thenThrow(new Exception());

        ResponseEntity<UserResponse> response = authenticationController.login(userRequest);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void test_getUserById() {
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        UserResponse userResponse = Mockito.mock(UserResponse.class);

        Mockito.when(authenticationService.getUserById("test_user")).thenReturn(userResponse);

        UserResponse response = authenticationController.getUserById("test_user");

        assertEquals(response, userResponse);
    }

    @Test
    void test_updateBankAccountDetails() {
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(authenticationService.updateUserBankDetails("test_user", "bank_code" ,"12345")).thenReturn(genericResponse);

        ResponseEntity<GenericResponse> response = authenticationController.updateBankAccountDetails("test_user", "bank_code" ,"12345");

        assertEquals(response.getBody(), genericResponse);
    }
}