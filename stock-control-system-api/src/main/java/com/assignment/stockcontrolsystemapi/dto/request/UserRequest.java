package com.assignment.stockcontrolsystemapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String username;

    private String password;

    private String companyName;

    private String email;

    private String phoneNumber;

    private String address;

    private String status;

    private String role;

    private String bankCode;

    private String bankAccountNumber;

}
