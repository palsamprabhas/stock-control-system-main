package com.assignment.stockcontrolsystemapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory implements Cloneable {
    @Id
    private String id;
    private String itemId;
    private int count;
    private Double totalPrice;
    private String status;
    private String supplierId;
    private String rackId;
}
