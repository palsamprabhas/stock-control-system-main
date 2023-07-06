package com.assignment.stockcontrolsystemapi.controller;

import com.assignment.stockcontrolsystemapi.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/overall-consumers-report")
    public List<Long> getOverallConsumersReport() {
        return dashboardService.getOverallConsumersReport();
    }

    @GetMapping("/overall-suppliers-report")
    public List<Long> getOverallSuppliersReport() {
        return dashboardService.getOverallSuppliersReport();
    }
}
