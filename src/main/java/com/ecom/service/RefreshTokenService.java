package com.ecom.service;

import com.ecom.entities.RefreshToken;

public interface RefreshTokenService {
	public RefreshToken findByToken(String token);
	public RefreshToken findByUser(int userId);
	public RefreshToken save(int userId);
	public boolean verifyExpiration(RefreshToken token);
	public void delete(int id);
	RefreshToken joinUser(int id);
}
