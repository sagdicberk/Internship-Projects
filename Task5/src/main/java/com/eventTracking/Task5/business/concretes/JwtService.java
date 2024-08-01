package com.eventTracking.Task5.business.concretes;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eventTracking.Task5.business.dto.UserDto;
import com.eventTracking.Task5.core.util.jwt.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
	
    private final AuthenticationManager authenticationManager;
	
    private final JwtUtil jwtUtil;
    
    private final UserDetailsManager userDetailsService;
    
    public String authenticateAndGenerateToken(UserDto userDto) {
    	Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
        );

        if (authentication.isAuthenticated()) {
            
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
            return jwtUtil.generateToken(userDetails);
        } else {
            throw new UsernameNotFoundException("Geçersiz kullanıcı adı");
        }
    }
}
