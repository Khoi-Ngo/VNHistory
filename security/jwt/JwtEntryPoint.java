package com.swp.vnhistory.security.jwt;

import java.io.IOException;
import ch.qos.logback.classic.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{

	private static final org.jboss.logging.Logger logger = LoggerFactory.logger(JwtEntryPoint.class);
	//ghi lại log của class
	
	//class này là bắt các token chết ngay từ đầu
	
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		logger.error("Unauthorized error Message: {}", authException.getMessage(), authException);
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error -> Unauthorized");
	}
	
	
}
