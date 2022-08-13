package com.catalyst.billingapp.dtos.requests;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String email;
    private String firstName;
    private String lastName;
}
