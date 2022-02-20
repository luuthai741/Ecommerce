package com.ecom.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ServerErrorException;

import com.ecom.exception.BadRequestException;
import com.ecom.exception.JWTTokenException;
import com.ecom.exception.NotFoundResourceException;
import com.ecom.exception.RefreshTokenException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

@RestControllerAdvice
public class CustomeExceptionHandler {
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleBadRequestException(BadRequestException ex, WebRequest req) {
		return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	@ExceptionHandler(value = RefreshTokenException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorMessage handleTokenRefreshException(RefreshTokenException ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.FORBIDDEN.value(), ex.getMessage());
	}

	@ExceptionHandler(NotFoundResourceException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage handleNotFoundException(NotFoundResourceException ex, WebRequest req) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(ServerErrorException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage handleSeverErrorException(ServerErrorException ex, WebRequest req) {
		return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}

	// @ExceptionHandler(value = AuthenticationException.class)
	// @ResponseStatus(HttpStatus.UNAUTHORIZED)
	// public ErrorMessage handleAuthenEx(RuntimeException ex, WebRequest request) {
	// return new ErrorMessage(HttpStatus.UNAUTHORIZED.value(),ex.getMessage());
	// }
	@ExceptionHandler(value = JWTTokenException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ErrorMessage handleJWTTokenException(RuntimeException ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
	}
	// @ExceptionHandler(value = AccessDeniedException.class)
	// @ResponseStatus(HttpStatus.FORBIDDEN)
	// public ErrorMessage handleAccessDeniedException(AccessDeniedException ex,
	// WebRequest request) {
	// return new ErrorMessage(HttpStatus.FORBIDDEN.value(), new Date(),
	// ex.getMessage(),
	// request.getDescription(false));
	// }
}
