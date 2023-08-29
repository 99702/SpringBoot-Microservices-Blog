package com.microservicesblog.authentication;

import com.microservicesblog.authentication.model.UserDetaiImpl;
import com.microservicesblog.databases.microservicesblogdb.entity.BlogUsers;
import com.microservicesblog.databases.microservicesblogdb.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BlogUsers user = userRepository.findUserByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Retrieve user roles/authorities and convert them to GrantedAuthority objects
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new UserDetaiImpl(user, authorities);
    }

    public BlogUsers findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
    }
}
