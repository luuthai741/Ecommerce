package com.ecom.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInResponse {
	private String accessToken;
	private String refreshToken;
	private int id;
	private String username;
	private String email;
	private List<String> roles;
	private String type = "Bearer";
	
	public SignInResponse(String accessToken, String refreshToken, int id, String username, String email,
			List<String> roles) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
}
