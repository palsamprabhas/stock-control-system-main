package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.dto.request.UserRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.UserResponse;
import com.assignment.stockcontrolsystemapi.model.User;
import com.assignment.stockcontrolsystemapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private UserRepository userRepository;

    @Test
    void test_login() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setPassword("test");
        User user = new User();
        user.setStatus("APPROVED");
        user.setPassword("test");

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));

        UserResponse response = authenticationService.login(userRequest);

        assertNotNull(response);
    }

    @Test
    void test_login_user_not_found() {
        UserRequest userRequest = new UserRequest();

        assertThrows(Exception.class, () -> {
            authenticationService.login(userRequest);
        });
    }

    @Test
    void test_login_user_request_not_approved() {
        UserRequest userRequest = new UserRequest();
        userRequest.setPassword("test");
        User user = new User();
        user.setStatus("REJECTED");
        user.setPassword("test");

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));


        assertThrows(Exception.class, () -> {
            authenticationService.login(userRequest);
        });

    }

    @Test
    void test_login_invalid_password() {
        UserRequest userRequest = new UserRequest();
        userRequest.setPassword("test");
        User user = new User();
        user.setStatus("APPROVED");
        user.setPassword("test_wrong");

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));


        assertThrows(Exception.class, () -> {
            authenticationService.login(userRequest);
        });

    }

    @Test
    void test_register() {
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        User user = Mockito.mock(User.class);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        UserResponse response = authenticationService.register(userRequest);

        assertNotNull(response);
    }

    @Test
    void test_getUserById() {
        User user = Mockito.mock(User.class);

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));

        UserResponse response = authenticationService.getUserById("test_user");

        assertNotNull(response);
    }

    @Test
    void test_updateUserBankDetails() {
        User user = Mockito.mock(User.class);

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));

        GenericResponse response = authenticationService.updateUserBankDetails("test_user", "code", "account_number");

        assertNotNull(response.getDescription(), "user updated successfully");
    }

    @Test
    void test_updateUserBankDetails_user_not_found() {

        GenericResponse response = authenticationService.updateUserBankDetails("test_user", "code", "account_number");

        assertNotNull(response.getDescription(), "user not found");
    }
}