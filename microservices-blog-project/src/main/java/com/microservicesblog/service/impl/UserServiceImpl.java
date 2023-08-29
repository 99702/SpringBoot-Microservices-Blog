package com.microservicesblog.service.impl;

import com.microservicesblog.authentication.model.UserDetaiImpl;
import com.microservicesblog.databases.microservicesblogdb.entity.BlogUsers;
import com.microservicesblog.databases.microservicesblogdb.repo.UserRepository;
import com.microservicesblog.dto.request.UserRequestDto;
import com.microservicesblog.exception.ProcessNotAllowedException;
import com.microservicesblog.exception.ServerResponse;
import com.microservicesblog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<Object> createUser(UserRequestDto userRequestDto) {
        ServerResponse serverResponse=new ServerResponse();
        try {
            BlogUsers blogUsers = new BlogUsers();
            Long count=userRepository.checkIfEmailExists(userRequestDto.getEmail());
            if(count>0) throw new ProcessNotAllowedException("Email already exists");
            blogUsers.setEmail(userRequestDto.getEmail());
            blogUsers.setName(userRequestDto.getName());
            blogUsers.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
            userRepository.save(blogUsers);
            return ServerResponse.successResponse("User Created Successfully");
        }
        catch (Exception e){
            serverResponse.setMessage(e.getMessage());
            serverResponse.setSuccess(Boolean.FALSE);
        }
         return new ResponseEntity<>(serverResponse,HttpStatus.BAD_REQUEST);
    }

    @Override
    public BlogUsers getLoggedInUser() {
        UserDetaiImpl userInfo= (UserDetaiImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userInfo.getLoggedInUser();
    }
}
