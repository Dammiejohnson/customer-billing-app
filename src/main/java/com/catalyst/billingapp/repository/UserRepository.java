package com.catalyst.billingapp.repository;

import com.catalyst.billingapp.dtos.responses.UserDto;
import com.catalyst.billingapp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
