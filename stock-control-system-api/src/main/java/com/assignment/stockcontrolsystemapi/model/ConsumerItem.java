package com.assignment.stockcontrolsystemapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumerItem {
    private String id;
    private String itemId;
    private int count;
    private String consumerId;
    private String status;
    private String stockId;
    private Double totalPrice;
}
