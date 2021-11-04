package com.stacksimplify.restservices.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservices.entities.View;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
//_embedded list
@Relation(collectionRelation = "orders", itemRelation = "order")
public class OrderDetailsWithId extends OrderDetails{
    @JsonView(View.Internal.class)
    private Long id;


}
