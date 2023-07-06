package com.assignment.stockcontrolsystemapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumerItemRequest {
    private String id;
    private String itemId;
    private int count;
    private String consumerId;
    private String status;
    private String stockId;
    private Double totalPrice;
}
