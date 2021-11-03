package com.stacksimplify.restservices.dtos;

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
    private Long id;


}
