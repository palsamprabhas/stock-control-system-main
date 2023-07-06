package com.assignment.stockcontrolsystemapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
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
