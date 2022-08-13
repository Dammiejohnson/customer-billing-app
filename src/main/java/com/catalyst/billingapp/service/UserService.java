package com.catalyst.billingapp.service;

import com.catalyst.billingapp.dtos.requests.UserRequest;
import com.catalyst.billingapp.dtos.responses.UserResponse;
import com.catalyst.billingapp.models.User;

import java.util.List;

public interface UserService {
    UserResponse save(UserRequest request);
    void deleteAll();
    long count();
    User retrieveACustomerBy(String email);
    List<User> retrieveAllCustomers();
}
