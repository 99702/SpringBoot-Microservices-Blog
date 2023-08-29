package com.microservicesblog.dto.request;

import com.microservicesblog.constant.ValidationConstant;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Data
public class UserLoginRequest {
    
    @NotBlank(message = "Email cannot be blank")
    @Pattern(regexp = ValidationConstant.EMAIL_ID_REGEX, message = "enter valid email")
    private String email;
    private String password;
}
