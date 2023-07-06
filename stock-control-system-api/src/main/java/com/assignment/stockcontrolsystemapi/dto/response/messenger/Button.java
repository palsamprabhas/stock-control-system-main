package com.assignment.stockcontrolsystemapi.dto.response.messenger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Button {
    private String type;
    private String title;
    private String payload;
}
