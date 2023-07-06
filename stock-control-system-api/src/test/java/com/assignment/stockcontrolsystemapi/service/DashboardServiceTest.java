package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.dto.response.UserResponse;
import com.assignment.stockcontrolsystemapi.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DashboardServiceTest {

    @InjectMocks
    private DashboardService dashboardService;

    @Mock
    private ConsumerService consumerService;
    @Mock
    private SupplierService supplierService;

    @Test
    void test_getOverallConsumersReport() {
        Mockito.when(consumerService.getAllConsumers()).thenReturn(List.of(
            UserResponse.builder().status("APPROVED").build(),
            UserResponse.builder().status("REJECTED").build(),
            UserResponse.builder().status("PENDING").build()
        ));

       List<Long> response = dashboardService.getOverallConsumersReport();

       assertEquals(response.size(), 3);
       assertEquals(response.get(0), 1);
       assertEquals(response.get(1), 1);
       assertEquals(response.get(2), 1);
    }

    @Test
    void test_getOverallSuppliersReport() {
        Mockito.when(supplierService.getAllSupplier()).thenReturn(List.of(
            UserResponse.builder().status("APPROVED").build(),
            UserResponse.builder().status("REJECTED").build(),
            UserResponse.builder().status("PENDING").build()
        ));

        List<Long> response = dashboardService.getOverallSuppliersReport();

        assertEquals(response.size(), 3);
        assertEquals(response.get(0), 1);
        assertEquals(response.get(1), 1);
        assertEquals(response.get(2), 1);
    }
}