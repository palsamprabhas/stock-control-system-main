package com.assignment.stockcontrolsystemapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {
    private String id;
    private String itemId;
    private int count;
    private String status;
    private Double totalPrice;
    private ItemResponse item;
    private String supplierId;
    private String rackId;
}
