package com.microservicesblog.dto.response;

import lombok.Data;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Data
public class BlogResponse {

    private Long id;
    private String body;
    private String title;
}
