package com.microservicesblog.databases.microservicesblogdb.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Entity
@Table(name = "user")
@Data
public class BlogUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<BlogUserRoles> roles = new HashSet<>();

}
