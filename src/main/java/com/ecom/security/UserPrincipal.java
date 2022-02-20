package com.ecom.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecom.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

public class UserPrincipal implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	@Getter
	private int id;
	private String username;
	@Getter
	private String email;
	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> roles;
	private boolean enabled;
	
	private UserPrincipal(int id,String username, String email, String password, Collection<? extends GrantedAuthority> roles,
			boolean enabled) {;
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.enabled = enabled;
	}


	public static UserPrincipal build(User user) {
		List<GrantedAuthority> roles = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().toString()))
				.collect(Collectors.toList());
		return new UserPrincipal(user.getId(),user.getUsername(),user.getEmail(),user.getPassword(),roles,user.isEnabled());
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
}