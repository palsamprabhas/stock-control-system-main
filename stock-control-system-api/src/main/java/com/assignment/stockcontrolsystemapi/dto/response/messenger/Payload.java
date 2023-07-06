package com.assignment.stockcontrolsystemapi.dto.response.messenger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payload {
    private String template_type;
    private List<PayloadElement> elements;

}
