package com.stacksimplify.restservices.dtos;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservices.entities.View;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

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
public class UserDetailsWithId extends UserDetails{
    @JsonView(View.External.class)
    private Long id;
    @JsonView(View.External.class)
    private List<OrderDetailsWithId> orders;
}
