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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SupplierServiceTest {
    @InjectMocks
    private SupplierService supplierService;

    @Mock
    private UserRepository userRepository;

    @Test
    void test_getAllSuppliers() {
        User supplier = new User();
        supplier.setRole(Constants.SUPPLIER);

        Mockito.when(userRepository.findAll()).thenReturn(List.of(supplier));

        List<UserResponse> responses = supplierService.getAllSupplier();

        assertEquals(responses.size(), 1);
    }

    @Test
    void test_getSupplierById() {
        User supplier = Mockito.mock(User.class);

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(supplier));

        UserResponse response = supplierService.getSupplierById("test_id");

        assertNotNull(response);
    }

    @Test
    void test_updateSupplier() {
        User supplier = Mockito.mock(User.class);
        UserRequest supplierRequest = Mockito.mock(UserRequest.class);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(supplier);
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(supplier));

        GenericResponse response = supplierService.updateSupplier(supplierRequest);

        assertEquals(response.getDescription(), "supplier updated successfully");
    }

    @Test
    void test_updateSupplier_not_found() {
        UserRequest supplierRequest = Mockito.mock(UserRequest.class);

        GenericResponse response = supplierService.updateSupplier(supplierRequest);

        assertEquals(response.getDescription(), "supplier not found");
    }

    @Test
    void test_updateSupplierStatus() {
        User supplier = Mockito.mock(User.class);
        UserRequest supplierRequest = Mockito.mock(UserRequest.class);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(supplier);
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(supplier));

        GenericResponse response = supplierService.updateSupplierStatus("test_user", "APPROVED");

        assertEquals(response.getDescription(), "supplier updated successfully");
    }

    @Test
    void test_updateSupplierStatus_not_found() {
        UserRequest supplierRequest = Mockito.mock(UserRequest.class);

        GenericResponse response = supplierService.updateSupplierStatus("test_user", "APPROVED");

        assertEquals(response.getDescription(), "supplier not found");
    }

    @Test
    void test_deleteSupplierById() {

        Mockito.doNothing().when(userRepository).deleteById(Mockito.any());

        GenericResponse response = supplierService.deleteSupplierById("test_id");

        assertEquals(response.getDescription(), "supplier deleted successfully");
    }
}