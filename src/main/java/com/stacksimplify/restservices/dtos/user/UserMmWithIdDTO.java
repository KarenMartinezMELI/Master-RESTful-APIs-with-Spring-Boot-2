package com.stacksimplify.restservices.dtos.user;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.stacksimplify.restservices.dtos.order.OrderMmWithIdDTO;
import com.stacksimplify.restservices.dtos.user.UserMmDTO;
import com.stacksimplify.restservices.entities.View;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
//_embedded list
@Relation(collectionRelation = "users", itemRelation = "user")
//MappingJacksonValue
//JsonFilter("userFilter")

public class UserMmWithIdDTO extends UserMmDTO {
    @JsonView(View.External.class)
    private Long id;
    @JsonView(View.External.class)
    @JacksonXmlElementWrapper(localName = "orders")
    @JacksonXmlProperty(localName = "order")
    private List<OrderMmWithIdDTO> orders;
}
