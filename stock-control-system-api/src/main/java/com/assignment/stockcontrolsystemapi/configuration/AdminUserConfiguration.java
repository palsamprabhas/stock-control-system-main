package com.assignment.stockcontrolsystemapi.configuration;

import com.assignment.stockcontrolsystemapi.model.User;
import com.assignment.stockcontrolsystemapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminUserConfiguration implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Value("${admin.user}")
    private String username;
    @Value("${admin.password}")
    private String password;
    @Value("${admin.bankCode}")
    private String bankCode;
    @Value("${admin.bankAccountNumber}")
    private String bankAccountNumber;


    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setBankCode(bankCode);
        user.setBankAccountNumber(bankAccountNumber);
        user.setRole("ADMIN");
        user.setStatus("APPROVED");
        userRepository.save(user);
    }
}