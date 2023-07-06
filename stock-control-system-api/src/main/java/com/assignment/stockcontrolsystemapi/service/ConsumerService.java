package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.dto.request.UserRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.UserResponse;
import com.assignment.stockcontrolsystemapi.model.User;
import com.assignment.stockcontrolsystemapi.repository.UserRepository;
import com.assignment.stockcontrolsystemapi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsumerService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> getAllConsumers() {
        return userRepository.findAll()
            .stream()
            .filter(user -> Constants.CONSUMER.equals(user.getRole()))
            .map(user ->
                UserResponse
                    .builder()
                    .username(user.getUsername())
                    .companyName(user.getCompanyName())
                    .address(user.getAddress())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .role(user.getRole())
                    .status(user.getStatus())
                    .role(user.getRole())
                    .bankCode(user.getBankCode())
                    .bankAccountNumber(user.getBankAccountNumber())
                    .build())
            .collect(Collectors.toList());
    }

    public UserResponse getConsumerById(String username) {
        return userRepository.findById(username)
            .map(user ->
                UserResponse
                    .builder()
                    .username(user.getUsername())
                    .companyName(user.getCompanyName())
                    .address(user.getAddress())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .role(user.getRole())
                    .status(user.getStatus())
                    .role(user.getRole())
                    .bankCode(user.getBankCode())
                    .bankAccountNumber(user.getBankAccountNumber())
                    .build())
            .get();
    }

    public GenericResponse updateConsumer(UserRequest userRequest) {
        Optional<User> consumer = userRepository.findById(userRequest.getUsername());
        if (consumer.isPresent()) {
            User updatedConsumer = User.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .companyName(userRequest.getCompanyName())
                .address(userRequest.getAddress())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .role(userRequest.getRole())
                .status(userRequest.getStatus())
                .role(userRequest.getRole())
                .bankCode(userRequest.getBankCode())
                .bankAccountNumber(userRequest.getBankAccountNumber())
                .build();
            userRepository.save(updatedConsumer);

            return new GenericResponse(Constants.SUCCESS, "consumer updated successfully");
        } else {
            return new GenericResponse(Constants.ERROR, "consumer not found");
        }
    }

    public GenericResponse updateConsumerStatus(String username, String status) {
        Optional<User> consumerOptional = userRepository.findById(username);
        if (consumerOptional.isPresent()) {
            User consumer = consumerOptional.get();
            User updatedConsumer = User.builder()
                .username(consumer.getUsername())
                .password(consumer.getPassword())
                .companyName(consumer.getCompanyName())
                .address(consumer.getAddress())
                .email(consumer.getEmail())
                .phoneNumber(consumer.getPhoneNumber())
                .role(consumer.getRole())
                .status(status)
                .role(consumer.getRole())
                .bankCode(consumer.getBankCode())
                .bankAccountNumber(consumer.getBankAccountNumber())
                .build();
            userRepository.save(updatedConsumer);

            return new GenericResponse(Constants.SUCCESS, "consumer updated successfully");
        } else {
            return new GenericResponse(Constants.ERROR, "consumer not found");
        }
    }

    public GenericResponse deleteConsumerById(String username) {
        userRepository.deleteById(username);
        return new GenericResponse(Constants.SUCCESS, "consumer deleted successfully");
    }
}
