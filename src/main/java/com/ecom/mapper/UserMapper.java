package com.ecom.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecom.dto.UserDTO;
import com.ecom.entities.Role;
import com.ecom.entities.User;
import com.ecom.repo.RoleRepo;
import com.ecom.request.SignUpRequest;

@Component
public class UserMapper {
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private RoleRepo roleRepo;

	public User signUp(SignUpRequest request) {
		User user = new User();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(encoder.encode(request.getPassword()));
		user.setEnabled(true);
		Role role = roleRepo.findByName("ROLE_USER");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		
		return user;
	}
	
	public UserDTO mapToUserDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		Set<Role> roles = user.getRoles();
		List<String> rolesDTO = roles.stream().map(x-> x.getName()).collect(Collectors.toList());
		dto.setRoles(rolesDTO);
		
		return dto;
	}
	
	public List<UserDTO> mapToUserDTO(List<User> userList){
		return userList.stream().map(x -> this.mapToUserDTO(x)).collect(Collectors.toList());
	}
}

