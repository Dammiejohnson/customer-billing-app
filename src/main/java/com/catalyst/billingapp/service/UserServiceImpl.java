package com.catalyst.billingapp.service;

import com.catalyst.billingapp.dtos.requests.UserRequest;
import com.catalyst.billingapp.dtos.responses.UserResponse;
import com.catalyst.billingapp.exceptions.CustomerServiceException;
import com.catalyst.billingapp.models.BillingDetails;
import com.catalyst.billingapp.models.User;
import com.catalyst.billingapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserResponse save(UserRequest request) {
        Optional<User> optionalUser = userRepository.findById(request.getEmail());

        if(optionalUser.isPresent()){
            throw new CustomerServiceException("email already exist");
        }

        if(!isValidEmail(request.getEmail())) throw new CustomerServiceException("Invalid details");
        else {
            User user = User.builder()
                    .email(request.getEmail())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .billingDetails(new BillingDetails(generateAccountNumber(), BigDecimal.ONE))
                    .build();
            User savedUser = userRepository.save(user);

            UserResponse response = new UserResponse();
            response.setEmail(savedUser.getEmail());
            response.setAccountNumber(savedUser.getBillingDetails().getAccountNumber());
            response.setTariff(savedUser.getBillingDetails().getTariff());

            return response;
        }

    }

    private boolean isValidEmail(String email){
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        boolean isValid;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        isValid = matcher.matches();

        return isValid;
    }

    private String generateAccountNumber(){
        String [] accountNumber = {"0","1","2","3","4","5","6","7","8","9"};
        String [] userAccountNumber = new String [10];
        Random randomNumbers = new Random();
        StringBuilder numberGenerator = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            userAccountNumber[i] = accountNumber[randomNumbers.nextInt(10)];
        }

        for (int i = 0; i < userAccountNumber.length; i++) {
            numberGenerator.append(userAccountNumber[randomNumbers.nextInt(10)]);
        }
        numberGenerator.append("-01");
        return numberGenerator.toString();
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public User retrieveACustomerBy(String email) {
        return null;
    }

    @Override
    public List<User> retrieveAllCustomers() {
        return null;
    }
}
