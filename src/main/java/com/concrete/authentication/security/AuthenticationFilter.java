package com.concrete.authentication.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class AuthenticationFilter extends GenericFilterBean {
    
	@Autowired
    TokenAuthenticationService tokenAuthenticationService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	System.out.println("Passou no AuthenticationFilter");
    	
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
        Authentication authentication = tokenAuthenticationService.getAuthentication(httpRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
        SecurityContextHolder.getContext().setAuthentication(null);
    	
    	
    	
//    	UserAuthentication userDetails = tokenAuthenticationService.getAuthentication((HttpServletRequest)request);
//    	
//    	if (userDetails != null) {
//    		UsernamePasswordAuthenticationToken authentication =
//                  new UsernamePasswordAuthenticationToken(userDetails.getName(), userDetails.getPassword());
//
//          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
//          SecurityContextHolder.getContext().setAuthentication(authentication);
//    	}
//
//        chain.doFilter(request, response);
    }
}