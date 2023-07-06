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
public class PayloadElement {
    private String title;
    private String subtitle;
    private String image_url;
    private List<Button> buttons;
}
