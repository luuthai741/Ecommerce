package com.ecom.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.RefreshTokenResponse;
import com.ecom.dto.SignInResponse;
import com.ecom.entities.RefreshToken;
import com.ecom.entities.User;
import com.ecom.exception.RefreshTokenException;
import com.ecom.request.SignInRequest;
import com.ecom.request.SignUpRequest;
import com.ecom.security.UserPrincipal;
import com.ecom.service.RefreshTokenService;
import com.ecom.service.UserService;
import com.ecom.utils.JWTUtils;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenResource {
	private static final Logger logger = LoggerFactory.getLogger(AuthenResource.class);
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private RefreshTokenService tokenService;

	@PostMapping("/signup")
	public ResponseEntity<?> register(@RequestBody SignUpRequest request) {
		if (userService.existsByUsername(request.getUsername())) {
			return ResponseEntity.badRequest().body("Tên tài khoản đã được sử dụng");
		}
		if (userService.existsByEmail(request.getEmail())) {
			return ResponseEntity.badRequest().body("Địa chỉ email đã được sử dụng");
		}
		userService.save(request);
		return new ResponseEntity<>("Tạo tài khoản thành công", HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> login(@RequestBody SignInRequest request) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		String accessToken = jwtUtils.generateToken(userPrincipal.getUsername());
		List<String> roles = userPrincipal.getAuthorities().stream().map(role -> role.getAuthority())
				.collect(Collectors.toList());
		tokenService.delete(userPrincipal.getId());
		RefreshToken refreshToken = tokenService.save(userPrincipal.getId());
		return ResponseEntity.ok(new SignInResponse(accessToken,refreshToken.getToken(),userPrincipal.getId(),userPrincipal.getUsername(),userPrincipal.getEmail(),roles));
	}

	@PostMapping("/resetToken")
	public ResponseEntity<?> reset(@Valid @RequestParam String token) {
		RefreshToken refreshToken = tokenService.findByToken(token);
		if (refreshToken == null) {
			logger.error("Token not found {}", token);
			throw new RefreshTokenException("Not found in database!");
		} else {
			if (!tokenService.verifyExpiration(refreshToken)) {
				logger.error("Token exprinary {}", token);
				throw new RefreshTokenException("Refresh token has been expinary");
			} else {
				RefreshToken currentToken = tokenService.joinUser(refreshToken.getId());
				User user = currentToken.getUser();
				logger.info("Change accessToken");
				String accessToken = jwtUtils.generateToken(user.getUsername());
				return ResponseEntity.ok(new RefreshTokenResponse(accessToken, token));
			}
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestParam int id) {
		tokenService.delete(id);
		return ResponseEntity.ok("Logout success");
	}
}
