package com.assignment.stockcontrolsystemapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryRequest {

    private String id;
    private String itemId;
    private int count;
    private Double totalPrice;
    private String status;
    private String supplierId;
    private String rackId;
}
