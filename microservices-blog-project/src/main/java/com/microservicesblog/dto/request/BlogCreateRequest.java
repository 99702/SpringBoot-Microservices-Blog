package com.microservicesblog.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Data
public class BlogCreateRequest {

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 5, max = 120, message = "Minimium length is 5 where maximum length is 120")
    private String title;
    @NotBlank(message = "Body cannot be blank")
    private String body;
}
