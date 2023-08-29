package com.microservicesblog.dto.response;

import lombok.Data;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Data
public class LoginResponse {

    private String userName;
    private String token;

    public LoginResponse(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }
}
