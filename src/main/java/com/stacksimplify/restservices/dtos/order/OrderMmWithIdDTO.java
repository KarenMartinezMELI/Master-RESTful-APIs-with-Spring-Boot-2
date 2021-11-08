package com.stacksimplify.restservices.dtos.order;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservices.dtos.order.OrderMmDTO;
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
public class OrderMmWithIdDTO extends OrderMmDTO {
    @JsonView(View.Internal.class)
    private Long id;


}
