package com.ecom.dto;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private List<String> roles;
}