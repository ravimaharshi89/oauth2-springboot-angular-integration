package com.oauth.angular;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SimpleAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private String obtainDomain(HttpServletRequest request) {
		System.out.println(request.getParameter("data"));
        return request.getParameter("data");
    }
}
