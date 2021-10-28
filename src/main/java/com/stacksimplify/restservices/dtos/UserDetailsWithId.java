package com.stacksimplify.restservices.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserDetailsWithId extends UserDetails{
    private Long id;
}
