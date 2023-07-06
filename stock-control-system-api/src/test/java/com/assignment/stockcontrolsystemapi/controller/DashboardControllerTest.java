package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.service.DashboardService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class DashboardControllerTest {

    @InjectMocks
    private DashboardController dashboardController;

    @Mock
    private DashboardService dashboardService;

    @Test
    void test_getOverallConsumersReport() {
        List<Long> report = List.of(10l,10l,20l);
        Mockito.when(dashboardService.getOverallSuppliersReport()).thenReturn(report);

        List<Long> response = dashboardController.getOverallSuppliersReport();

        assertEquals(response, report);
    }

    @Test
    void test_getOverallSuppliersReport() {
        List<Long> report = List.of(10l,10l,20l);
        Mockito.when(dashboardService.getOverallConsumersReport()).thenReturn(report);

        List<Long> response = dashboardController.getOverallConsumersReport();

        assertEquals(response, report);
    }
}