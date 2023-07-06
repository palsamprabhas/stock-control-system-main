package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.dto.request.UserRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.UserResponse;
import com.assignment.stockcontrolsystemapi.model.User;
import com.assignment.stockcontrolsystemapi.repository.UserRepository;
import com.assignment.stockcontrolsystemapi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse login(UserRequest userRequest) throws Exception {
        Optional<User> user = userRepository.findById(userRequest.getUsername());
        if (user.isPresent()) {
            if (!user.get().getStatus().equals("APPROVED")) {
                throw new Exception("Your request is not approved yet");
            }
            if (user.get().getPassword().equals(userRequest.getPassword())) {
                return UserResponse.builder()
                    .username(user.get().getUsername())
                    .role(user.get().getRole())
                    .build();
            } else {
                throw new Exception("invalid password");
            }

        } else {
            throw new Exception("user not found");
        }
    }

    public UserResponse register(UserRequest userRequest) {
        User user = User.builder()
            .username(userRequest.getUsername())
            .password(userRequest.getPassword())
            .companyName(userRequest.getCompanyName())
            .address(userRequest.getAddress())
            .email(userRequest.getEmail())
            .phoneNumber(userRequest.getPhoneNumber())
            .role(userRequest.getRole())
            .status("PENDING")
            .role(userRequest.getRole())
            .build();

        User response = userRepository.save(user);

        return UserResponse.builder()
            .username(response.getUsername())
            .address(response.getAddress())
            .email(response.getEmail())
            .phoneNumber(response.getPhoneNumber())
            .role(response.getRole())
            .status("PENDING")
            .role(response.getRole())
            .build();
    }

    public UserResponse getUserById(String username) {
        Optional<User> userOptional = userRepository.findById(username);
        User user = userOptional.get();
        return UserResponse.builder()
            .username(user.getUsername())
            .address(user.getAddress())
            .email(user.getEmail())
            .phoneNumber(user.getPhoneNumber())
            .role(user.getRole())
            .status(user.getStatus())
            .bankCode(user.getBankCode())
            .bankAccountNumber(user.getBankAccountNumber())
            .build();

    }

    public GenericResponse updateUserBankDetails(String username, String bankCode, String bankAccountNumber) {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            User updateduser = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .companyName(user.getCompanyName())
                .address(user.getAddress())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .status(user.getStatus())
                .role(user.getRole())
                .bankCode(bankCode)
                .bankAccountNumber(bankAccountNumber)
                .build();
            userRepository.save(updateduser);

            return new GenericResponse(Constants.SUCCESS, "user updated successfully");
        } else {
            return new GenericResponse(Constants.ERROR, "user not found");
        }
    }
}
