package com.assignment.stockcontrolsystemapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumerItemResponse {
    private String id;
    private String itemId;
    private int count;
    private String consumerId;
    private String status;
    private String stockId;
    private Double totalPrice;
    private ItemResponse item;
}
