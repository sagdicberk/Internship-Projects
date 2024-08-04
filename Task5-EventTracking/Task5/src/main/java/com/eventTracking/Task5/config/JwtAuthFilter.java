package com.eventTracking.Task5.config;

import java.io.IOException;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.eventTracking.Task5.business.abstracts.UserDetailsServiceImp;
import com.eventTracking.Task5.core.util.jwt.JwtUtil;
import com.eventTracking.Task5.model.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final UserDetailsServiceImp userDetailsServiceImp;

	public JwtAuthFilter(JwtUtil jwtUtil, @Lazy UserDetailsServiceImp userDetailsServiceImp) {
		super();
		this.jwtUtil = jwtUtil;
		this.userDetailsServiceImp = userDetailsServiceImp;
	}

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String email = null;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			email = jwtUtil.extractEmail(token);
			log.info("Token extracted: " + token);
			log.info("Email extracted: " + email);
		} else {
			log.warn("No Authorization header or invalid format");
		}
		
		

		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails user = userDetailsServiceImp.loadUserByUsername(email);
			log.info("User loaded: " + user.getUsername());
			if (jwtUtil.validateToken(token, user)) {
				log.info("Token validated successfully");
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,
						user.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			} else {
				log.warn("Token validation failed");
			}
		}
		filterChain.doFilter(request, response);
	}

}
