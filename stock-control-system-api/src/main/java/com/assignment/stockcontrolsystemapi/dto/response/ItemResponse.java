package com.assignment.stockcontrolsystemapi.dto.response;

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
public class ItemResponse {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private String expiryDate;
}
