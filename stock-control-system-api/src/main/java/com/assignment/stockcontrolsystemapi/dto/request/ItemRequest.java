package com.assignment.stockcontrolsystemapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemRequest {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private String expiryDate;
}
