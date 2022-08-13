package com.catalyst.billingapp.service;

import com.catalyst.billingapp.dtos.requests.UserRequest;
import com.catalyst.billingapp.dtos.responses.UserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    private UserRequest registerCustomer;

    @BeforeEach
    void setUp() {
        registerCustomer = UserRequest.builder()
                .email("test@gmail.com")
                .firstName("testFirst")
                .lastName("testLast")
                .build();
    }

    @Test
    void testThatCustomerCanBeRegistered(){
        UserResponse response=  userService.save(registerCustomer);
        assertThat(response.getEmail()).isEqualTo("test@gmail.com");
        assertThat(response.getTariff()).isEqualTo(BigDecimal.ONE);
    }

    @AfterEach
    void tearDown() {
    }
}