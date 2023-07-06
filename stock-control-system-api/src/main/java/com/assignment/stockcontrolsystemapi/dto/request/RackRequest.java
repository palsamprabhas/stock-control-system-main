package com.assignment.stockcontrolsystemapi.dto.request;

import com.assignment.stockcontrolsystemapi.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RackRequest {
    private String id;
    private String name;
    private boolean damagedRack;
}
