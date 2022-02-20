package com.ecom.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomeAccessDenied implements AccessDeniedHandler {
	private static final Logger logger = LoggerFactory.getLogger(CustomeAccessDenied.class);
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		logger.error("Lỗi truy cập tài nguyên {}",accessDeniedException.getMessage());
		response.sendError(response.SC_FORBIDDEN,"Bạn không có quyền truy cập vào tài nguyên này");
	}

}
