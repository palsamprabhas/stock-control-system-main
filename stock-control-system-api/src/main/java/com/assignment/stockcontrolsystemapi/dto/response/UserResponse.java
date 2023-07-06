package com.assignment.stockcontrolsystemapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String username;

    private String companyName;

    private String email;

    private String phoneNumber;

    private String address;

    private String status;

    private String role;

    private String bankCode;

    private String bankAccountNumber;

}
