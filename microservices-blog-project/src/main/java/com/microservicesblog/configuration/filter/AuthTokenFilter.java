package com.microservicesblog.configuration.filter;

import com.microservicesblog.authentication.CustomUserDetailsService;
import com.microservicesblog.authentication.model.UserDetaiImpl;
import com.microservicesblog.exception.ProcessNotAllowedException;
import com.microservicesblog.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Slf4j

public class AuthTokenFilter extends OncePerRequestFilter {


    @Autowired
    private JwtUtils jwtUtils;
    @Value("${user.jwtSecret}")
    private String jwtSecret;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            String username = null;

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

                username = jwtUtils.extractUsername(authorizationHeader.substring(7));
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetaiImpl userDetails = (UserDetaiImpl) userDetailsService.loadUserByUsername(username);
                if (Boolean.TRUE.equals(jwtUtils.validateToken(authorizationHeader.substring(7), userDetails))) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    throw new ProcessNotAllowedException("Token is invalid.");
                }
            }

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            log.error("Cannot set user authentication: {}", e.getMessage());
        }

    }
}
