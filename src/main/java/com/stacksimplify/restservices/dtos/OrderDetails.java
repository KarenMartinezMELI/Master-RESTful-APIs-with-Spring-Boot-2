package com.stacksimplify.restservices.dtos;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class OrderDetails extends RepresentationModel{
    private String description;
}
