package com.stacksimplify.restservices.dtos.order;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.stacksimplify.restservices.entities.View;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class OrderMmDTO extends RepresentationModel{
    @JsonView(View.Internal.class)
    private String description;
}
