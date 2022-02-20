package com.ecom.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dto.UserDTO;
import com.ecom.entities.User;
import com.ecom.mapper.UserMapper;
import com.ecom.repo.UserRepo;
import com.ecom.request.SignUpRequest;
import com.ecom.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findByUsername(String username) {
		User user = userRepo.findByUsername(username).orElse(null);
		if(user != null) {
			return user;
		}
		return null;
	}
	@Override
	public UserDTO findByEmail(String email) {
//		User user = userRepo.findByEmail(email).orElse(null);
//		if(user != null) {
//			return userMapper.mapToDTO(user);
//		}
		return null;
	}
	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.existsByEmail(email);
	}
	@Override
	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.existsByUsername(username);
	}
	@Override
	public void save(SignUpRequest request) {
		User user = userMapper.signUp(request);
		userRepo.save(user);
	}
	
	@Override
	public UserDTO getUserAndRoles(User user) {
		User currentUser = userRepo.findByIdAndGetRoles(user.getId()).orElse(null);
		if(currentUser!= null) {
			return userMapper.mapToUserDTO(currentUser);
		}
		return null;
	}
	@Override
	public List<UserDTO> getUserListWithRoles() {
		List<User> userList = userRepo.getUserListWithRoles();
		return userMapper.mapToUserDTO(userList);
	}
	@Override
	public void deteleByID(int id) {
		userRepo.deleteById(id);
	}
	


}