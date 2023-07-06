package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.dto.request.UserRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.UserResponse;
import com.assignment.stockcontrolsystemapi.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest userRequest) {
        return authenticationService.register(userRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest) {
        try {
            UserResponse userResponse = authenticationService.login(userRequest);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{username}")
    public UserResponse getUserById(@PathVariable String username) {
        return authenticationService.getUserById(username);
    }

    @PutMapping("/update-bank-details/{username}/{bankCode}/{bankAccountNumber}")
    public ResponseEntity<GenericResponse> updateBankAccountDetails(
        @PathVariable String username,
        @PathVariable String bankCode,
        @PathVariable String bankAccountNumber) {
        GenericResponse response = authenticationService.updateUserBankDetails(username, bankCode, bankAccountNumber);
        return ResponseEntity.ok(response);
    }
}
