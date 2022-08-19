package com.catalyst.billingapp.dtos.responses;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {
    private String email;
    private String accountNumber;
    private BigDecimal tariff;
}
