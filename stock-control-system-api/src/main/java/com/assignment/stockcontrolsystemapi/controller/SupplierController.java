package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.dto.request.UserRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.UserResponse;
import com.assignment.stockcontrolsystemapi.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public List<UserResponse> getAllSuppliers() {
        return supplierService.getAllSupplier();
    }

    @GetMapping("/{username}")
    public UserResponse getSupplierById(@PathVariable String username) {
        return supplierService.getSupplierById(username);
    }


    @PutMapping
    public GenericResponse updateSupplier(@RequestBody UserRequest userRequest) {
        return supplierService.updateSupplier(userRequest);
    }

    @PutMapping("/{username}/{status}")
    public GenericResponse updateSupplierStatus(@PathVariable String username, @PathVariable String status) {
        return supplierService.updateSupplierStatus(username, status);
    }


    @DeleteMapping("/{username}")
    public GenericResponse deleteSupplierById(@PathVariable String username) {
        return supplierService.deleteSupplierById(username);
    }

}
