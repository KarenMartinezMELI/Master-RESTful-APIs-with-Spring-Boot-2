package com.stacksimplify.restservices.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//Entity
//Per default the entity name is the name of the class
@Entity(name = "User")
//Same as before, but without camelcase
//@Table(name = "user", schema = "usermanagment")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name", length=50, nullable = false, unique = true)
    private String username;

    @Column(name = "first_name", length=50, nullable = false)
    private String firstname;

    @Column(name = "last_name", length=50, nullable = false)
    private String lastname;

    @Column(name = "email", length=50, nullable = false)
    private String email;

    @Column(name = "role", length=50)
    private String role;

    @Column(name = "ssn", length=50, unique = true)
    private String ssn;

    @OneToMany (mappedBy = "user")
    List<Order> orders;

}
