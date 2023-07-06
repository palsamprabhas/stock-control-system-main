package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.dto.request.ItemRequest;
import com.assignment.stockcontrolsystemapi.dto.request.UserRequest;
import com.assignment.stockcontrolsystemapi.dto.response.GenericResponse;
import com.assignment.stockcontrolsystemapi.dto.response.RackResponse;
import com.assignment.stockcontrolsystemapi.dto.response.UserResponse;
import com.assignment.stockcontrolsystemapi.service.SupplierService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SupplierControllerTest {

    @InjectMocks
    private SupplierController supplierController;

    @Mock
    private SupplierService supplierService;

    @Test
    void test_getAllSuppliers() {
        UserResponse userResponse = Mockito.mock(UserResponse.class);

        Mockito.when(supplierService.getAllSupplier()).thenReturn(List.of(userResponse));

        List<UserResponse> response = supplierController.getAllSuppliers();

        assertEquals(response.size(), 1);
    }

    @Test
    void test_getSupplierById() {
        UserResponse userResponse = Mockito.mock(UserResponse.class);

        Mockito.when(supplierService.getSupplierById("testId")).thenReturn(userResponse);

        UserResponse response = supplierController.getSupplierById("testId");

        assertEquals(response, userResponse);
    }

    @Test
    void test_updateSupplier() {
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(supplierService.updateSupplier(userRequest)).thenReturn(genericResponse);

        GenericResponse response = supplierController.updateSupplier(userRequest);

        assertEquals(response, genericResponse);
    }

    @Test
    void test_updateSupplierStatus() {
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(supplierService.updateSupplierStatus("testuser", "APPROVED")).thenReturn(genericResponse);

        GenericResponse response = supplierController.updateSupplierStatus("testuser", "APPROVED");

        assertEquals(response, genericResponse);
    }

    @Test
    void test_deleteSupplierById() {
        GenericResponse genericResponse = Mockito.mock(GenericResponse.class);

        Mockito.when(supplierService.deleteSupplierById("testId")).thenReturn(genericResponse);

        GenericResponse response = supplierController.deleteSupplierById("testId");

        assertEquals(response, genericResponse);
    }
}