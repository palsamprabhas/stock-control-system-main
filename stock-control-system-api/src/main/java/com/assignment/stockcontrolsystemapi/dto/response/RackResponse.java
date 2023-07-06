package com.assignment.stockcontrolsystemapi.dto.response;

import com.assignment.stockcontrolsystemapi.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RackResponse {
    private String id;
    private String name;
    private boolean damagedRack;
    private List<InventoryResponse> inventories;
}
