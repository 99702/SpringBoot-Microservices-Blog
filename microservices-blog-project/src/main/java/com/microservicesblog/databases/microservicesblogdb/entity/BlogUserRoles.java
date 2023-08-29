package com.microservicesblog.databases.microservicesblogdb.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Entity
@Table(name = "roles")
@Data
public class BlogUserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

}
