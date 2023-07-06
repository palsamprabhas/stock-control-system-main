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
public class QuickReplyResponse {
    private String text;
    private List<QuickReply> quick_replies;
}
