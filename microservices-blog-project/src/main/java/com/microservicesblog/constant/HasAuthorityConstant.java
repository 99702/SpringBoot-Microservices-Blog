package com.microservicesblog.constant;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
public class HasAuthorityConstant {
    public static final String DELETE_BLOG ="hasAuthority('ADMIN')";
    public static final String MODIFY_BLOG = "hasAnyAuthority('ADMIN','USER')";
}
