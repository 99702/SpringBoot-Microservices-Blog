package com.microservicesblog.controller;

import com.microservicesblog.constant.HasAuthorityConstant;
import com.microservicesblog.constant.PathConstant;
import com.microservicesblog.dto.request.BlogCreateRequest;
import com.microservicesblog.dto.request.BlogModifyRequest;
import com.microservicesblog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@RestController
@RequestMapping(PathConstant.BLOG)
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping(PathConstant.CREATE)
    public ResponseEntity<?> createBlog(@RequestBody BlogCreateRequest blogCreateRequest) {
        return blogService.createBlog(blogCreateRequest);
    }
    @GetMapping(PathConstant.FETCH_BY_ID+"/"+PathConstant.WRAP_ID)
    public ResponseEntity<?> getBlogById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(blogService.getBlogById(id), HttpStatus.ACCEPTED);
    }

    @PostMapping(PathConstant.MODIFY)
    public ResponseEntity<?> updateBlog( @RequestBody BlogModifyRequest blogModifyRequest) {
       return  blogService.modifyBlog(blogModifyRequest);
    }

    @DeleteMapping(PathConstant.DELETE+"/"+PathConstant.WRAP_ID)
    public ResponseEntity<?> deleteBlog(@PathVariable("id") Long id) {
        blogService.deleteBlog(id);
    return new ResponseEntity<>(blogService.deleteBlog(id),HttpStatus.OK);
    }
}
