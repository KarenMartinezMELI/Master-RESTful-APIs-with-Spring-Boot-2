package com.stacksimplify.restservices.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stacksimplify.restservices.dtos.order.OrderMmWithIdDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UserMsDTO {
    private Long id;
    private String username;
    @JsonProperty("role_name")
    private String rolename;
    @JsonProperty("email_adress")
    private String emailadress;
    private List<OrderMmWithIdDTO> orders;
}
