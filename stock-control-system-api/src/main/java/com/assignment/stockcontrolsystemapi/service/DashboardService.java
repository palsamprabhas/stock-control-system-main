package com.assignment.stockcontrolsystemapi.service;

import com.assignment.stockcontrolsystemapi.dto.response.UserResponse;
import com.assignment.stockcontrolsystemapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private SupplierService supplierService;

    public List<Long> getOverallConsumersReport() {
        List<UserResponse> consumers = consumerService.getAllConsumers();

        List<Long> consumerStatusReport = new ArrayList<>();
        consumerStatusReport.add(
            consumers.stream().filter(userResponse -> userResponse.getStatus().equals("APPROVED")
            ).count()
        );
        consumerStatusReport.add(
            consumers.stream().filter(userResponse -> userResponse.getStatus().equals("REJECTED")
            ).count()
        );
        consumerStatusReport.add(
            consumers.stream().filter(userResponse -> userResponse.getStatus().equals("PENDING")
            ).count()
        );
        return consumerStatusReport;
    }

    public List<Long> getOverallSuppliersReport() {
        List<UserResponse> suppliers = supplierService.getAllSupplier();

        List<Long> supplierStatusReport = new ArrayList<>();
        supplierStatusReport.add(
            suppliers.stream().filter(userResponse -> userResponse.getStatus().equals("APPROVED")
            ).count()
        );
        supplierStatusReport.add(
            suppliers.stream().filter(userResponse -> userResponse.getStatus().equals("REJECTED")
            ).count()
        );
        supplierStatusReport.add(
            suppliers.stream().filter(userResponse -> userResponse.getStatus().equals("PENDING")
            ).count()
        );
        return supplierStatusReport;
    }
}
