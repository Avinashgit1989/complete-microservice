package com.avijava.identity.service;

import com.avijava.identity.model.AuthenticationRequest;
import com.avijava.identity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public String createJwtToken(AuthenticationRequest authenticationRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            if (authentication.isAuthenticated()){
                final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
                return jwtUtil.generateToken(userDetails);
            } else {
                    throw new Exception("You are trying with Invalid username or password");
                }
    }

    public boolean validateToken(String token) throws Exception {
        try{
            jwtUtil.validateToken(token);
            return true;
        } catch (Exception e) {
            throw new Exception("Invalid JWT token", e);
        }
    }
}