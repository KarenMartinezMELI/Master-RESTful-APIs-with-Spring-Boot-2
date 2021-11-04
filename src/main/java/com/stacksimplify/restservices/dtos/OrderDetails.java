package com.stacksimplify.restservices.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservices.entities.View;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class OrderDetails extends RepresentationModel{
    @JsonView(View.Internal.class)
    private String description;
}
