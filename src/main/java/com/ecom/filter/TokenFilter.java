package com.ecom.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecom.exception.JWTTokenException;
import com.ecom.utils.JWTUtils;

import io.jsonwebtoken.ExpiredJwtException;

public class TokenFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private UserDetailsService userDetailsService;

	private String parseJWT(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
			if(headerAuth == null) {
				return null;
			}
			if (headerAuth.startsWith("Bearer ") && StringUtils.hasText(headerAuth)) {
				return headerAuth.substring(7, headerAuth.length());
			}
		return null;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = parseJWT(request);
			if (jwt != null && jwtUtils.validateToken(request,jwt)) {
				String username = jwtUtils.getUsernameFromToken(jwt);

				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
		} catch (JWTTokenException e) {
			logger.error("Token exception: {}", e.getMessage());
			request.setAttribute("JWTException", e);
		}
		filterChain.doFilter(request, response);
		
	}
}