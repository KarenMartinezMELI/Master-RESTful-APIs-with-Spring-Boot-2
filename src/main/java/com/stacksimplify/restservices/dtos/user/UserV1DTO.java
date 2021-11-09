package com.stacksimplify.restservices.dtos.user;

import com.stacksimplify.restservices.dtos.order.OrderMmWithIdDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserV1DTO {
    private Long id;
    private String username;
    private String role;
    private String email;
    private String ssn;
    private List<OrderMmWithIdDTO> orders;
}
