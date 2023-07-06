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
public class SupplierService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> getAllSupplier() {
        return userRepository.findAll()
            .stream()
            .filter(user -> Constants.SUPPLIER.equals(user.getRole()))
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

    public UserResponse getSupplierById(String username) {
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

    public GenericResponse updateSupplier(UserRequest userRequest) {
        Optional<User> supplier = userRepository.findById(userRequest.getUsername());
        if (supplier.isPresent()) {
            User updatedSupplier = User.builder()
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
            userRepository.save(updatedSupplier);

            return new GenericResponse(Constants.SUCCESS, "supplier updated successfully");
        } else {
            return new GenericResponse(Constants.ERROR, "supplier not found");
        }
    }

    public GenericResponse updateSupplierStatus(String username, String status) {
        Optional<User> supplierOptional = userRepository.findById(username);
        if (supplierOptional.isPresent()) {
            User supplier = supplierOptional.get();
            User updatedSupplier = User.builder()
                .username(supplier.getUsername())
                .password(supplier.getPassword())
                .companyName(supplier.getCompanyName())
                .address(supplier.getAddress())
                .email(supplier.getEmail())
                .phoneNumber(supplier.getPhoneNumber())
                .role(supplier.getRole())
                .status(status)
                .role(supplier.getRole())
                .bankCode(supplier.getBankCode())
                .bankAccountNumber(supplier.getBankAccountNumber())
                .build();
            userRepository.save(updatedSupplier);

            return new GenericResponse(Constants.SUCCESS, "supplier updated successfully");
        } else {
            return new GenericResponse(Constants.ERROR, "supplier not found");
        }
    }

    public GenericResponse deleteSupplierById(String username) {
        userRepository.deleteById(username);
        return new GenericResponse(Constants.SUCCESS, "supplier deleted successfully");
    }
}
