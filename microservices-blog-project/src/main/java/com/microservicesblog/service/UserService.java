package com.microservicesblog.service;

import com.microservicesblog.databases.microservicesblogdb.entity.BlogUsers;
import com.microservicesblog.dto.request.UserRequestDto;
import org.springframework.http.ResponseEntity;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
public interface UserService {

    ResponseEntity<Object> createUser(UserRequestDto userRequestDto);

    BlogUsers getLoggedInUser();
}
