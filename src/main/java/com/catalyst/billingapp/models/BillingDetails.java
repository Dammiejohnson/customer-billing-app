package com.catalyst.billingapp.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillingDetails {
    private String accountNumber;
    private BigDecimal tariff;
}
