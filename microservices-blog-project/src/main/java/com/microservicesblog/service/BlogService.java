package com.microservicesblog.service;

import com.microservicesblog.dto.request.BlogCreateRequest;
import com.microservicesblog.dto.request.BlogModifyRequest;
import com.microservicesblog.dto.response.BlogResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */

public interface BlogService {

    ResponseEntity<Object> createBlog(BlogCreateRequest blogCreateRequest);

    ResponseEntity<Object> modifyBlog(BlogModifyRequest blogCreateRequest);

    ResponseEntity<Object> deleteBlog(Long id);

    BlogResponse getBlogById(Long blogId);


}
