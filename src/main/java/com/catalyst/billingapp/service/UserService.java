package com.catalyst.billingapp.service;

import com.catalyst.billingapp.dtos.requests.UserRequest;
import com.catalyst.billingapp.dtos.responses.UserDto;
import com.catalyst.billingapp.dtos.responses.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse save(UserRequest request);
    void deleteAll();
    long count();
    UserDto retrieveACustomerBy(String email);
    List<UserDto> retrieveAllCustomers();
}
