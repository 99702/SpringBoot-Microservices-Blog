package com.microservicesblog.authentication.model;

import com.microservicesblog.databases.microservicesblogdb.entity.BlogUsers;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */

@Data
public class UserDetaiImpl implements UserDetails {

    @Getter
    private List<String> grantedAuthorities = Collections.emptyList();

    private final BlogUsers blogUsers;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetaiImpl(BlogUsers blogUsers, Collection<? extends GrantedAuthority> authorities) {
        this.blogUsers = blogUsers;
        this.authorities = authorities;
    }
    @Override
    public String getUsername() {
        return this.blogUsers.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        getGrantedAuthorities().forEach(p -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add(authority);
        });

        getGrantedAuthorities().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(r);
            authorities.add(authority);
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.blogUsers.getPassword();
    }

    public BlogUsers getLoggedInUser(){
        return blogUsers;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}