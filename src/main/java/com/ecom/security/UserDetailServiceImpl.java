package com.ecom.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecom.entities.User;
import com.ecom.repo.UserRepo;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Không thể tìm thấy người dùng với tên tài khoản "+ username));
		return UserPrincipal.build(user);
	}

}