package com.catalyst.billingapp.service;

import com.catalyst.billingapp.dtos.requests.UserRequest;
import com.catalyst.billingapp.dtos.responses.UserDto;
import com.catalyst.billingapp.dtos.responses.UserResponse;
import com.catalyst.billingapp.exceptions.CustomerServiceException;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
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

    @Test
    void testThatTheSameUserCannotRegisterTwice(){
            userService.save(registerCustomer);
        CustomerServiceException thrown = assertThrows(CustomerServiceException.class, () -> userService.save(registerCustomer));
        MatcherAssert.assertThat(thrown.getMessage(), is("email already exist"));
    }

    @Test
    void testThatInvalidEmailThrowsAnException(){
        UserRequest registerCustomer = UserRequest.builder()
                .email("testgmail.com")
                .firstName("testFirst")
                .lastName("testLast")
                .build();
        CustomerServiceException thrown = assertThrows(CustomerServiceException.class, () -> userService.save(registerCustomer));
        MatcherAssert.assertThat(thrown.getMessage(), is("Invalid details"));
    }

    @Test
    void testThatMoreThanOneUserCanRegister(){
           userService.save(registerCustomer);
        UserRequest registerCustomer2 = UserRequest.builder()
                .email("test2@gmail.com")
                .firstName("testFirst2")
                .lastName("testLast2")
                .build();
            userService.save(registerCustomer2);
            assertThat(userService.count()).isEqualTo(2L);
    }

    @Test
    void testThatRegisteredCustomerCanBeFound(){
        userService.save(registerCustomer);
        UserRequest registerCustomer2 = UserRequest.builder()
                .email("test2@gmail.com")
                .firstName("testFirst2")
                .lastName("testLast2")
                .build();
        userService.save(registerCustomer2);
        UserDto foundUser = userService.retrieveACustomerBy("test@gmail.com");
        UserDto foundUser2 = userService.retrieveACustomerBy("test2@gmail.com");
        assertThat(foundUser.getFirstName()).isEqualTo("testFirst");
        assertThat(foundUser2.getLastName()).isEqualTo("testLast2");
    }

    @Test
    void testThatAllRegisteredCustomersCanBeRetrived(){
        userService.save(registerCustomer);
        UserRequest registerCustomer2 = UserRequest.builder()
                .email("test2@gmail.com")
                .firstName("testFirst2")
                .lastName("testLast2")
                .build();
        userService.save(registerCustomer2);
        List<UserDto> allUsers = userService.retrieveAllCustomers();
        assertThat(allUsers.size()).isEqualTo(2L);
    }

    @AfterEach
    void tearDown() {
        userService.deleteAll();
    }
}