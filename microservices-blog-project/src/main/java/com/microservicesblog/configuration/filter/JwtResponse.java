package com.microservicesblog.configuration.filter;

import lombok.Getter;

import java.util.List;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    @Getter
    private final List<String> roles;
    @Getter
    private Long id;
    @Getter
    private String username;
    @Getter
    private String email;

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
