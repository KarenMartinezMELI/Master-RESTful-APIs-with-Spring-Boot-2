package com.stacksimplify.restservices.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OrderDetailsWithId extends OrderDetails{
    private Long id;
}
