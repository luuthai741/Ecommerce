package com.ecom.service;

import java.util.List;

import com.ecom.dto.UserDTO;
import com.ecom.entities.User;
import com.ecom.request.SignUpRequest;

public interface UserService {
	User findByUsername(String username);
	UserDTO findByEmail(String email);
	boolean existsByEmail(String email);
	boolean existsByUsername(String username);
	void save(SignUpRequest request);
	List<UserDTO> getUserListWithRoles();
	UserDTO getUserAndRoles(User user);
	void deteleByID(int id);
}
