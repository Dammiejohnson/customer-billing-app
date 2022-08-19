package com.catalyst.billingapp.dtos.responses;

import com.catalyst.billingapp.models.BillingDetails;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private BillingDetails billingDetails;
}
