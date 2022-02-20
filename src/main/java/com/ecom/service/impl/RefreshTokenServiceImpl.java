package com.ecom.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entities.RefreshToken;
import com.ecom.entities.User;
import com.ecom.exception.NotFoundResourceException;
import com.ecom.repo.RefreshTokenRepo;
import com.ecom.repo.UserRepo;
import com.ecom.service.RefreshTokenService;

@Service
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {
	private static final Logger logger = LoggerFactory.getLogger(RefreshTokenServiceImpl.class);
	@Autowired
	private RefreshTokenRepo tokenRepo;
	@Autowired
	private UserRepo userRepo;

	private long expirationMs = 43200000;
	
	@Override
	public RefreshToken findByToken(String token) {
		return tokenRepo.findByToken(token).orElseThrow(()-> new NotFoundResourceException("Cannt found the token" +token));
	}

	@Override
	public RefreshToken save(int userId) {
		User user = userRepo.findById(userId).get();
		RefreshToken token = tokenRepo.findByUser(user);
		if(token != null && verifyExpiration(token)) {
			return token;
		}else {
			token= new RefreshToken();
			token.setToken(UUID.randomUUID().toString());
			token.setUser(user);
			token.setExpiryDate(new Date(new Date().getTime() + expirationMs));
			tokenRepo.save(token);	
			
			return token;
		}		
	}

	@Override
	public boolean verifyExpiration(RefreshToken token) {
		Date now = new Date();
		if(now.compareTo(token.getExpiryDate()) <1) {
			return true;
		}
		return false;
	}


	@Override
	public RefreshToken joinUser(int id) {
		return tokenRepo.joinUser(id);
	}

	@Override
	public void delete(int id) {
		User user = userRepo.findById(id).orElseThrow(()-> new NotFoundResourceException("Not found user"));
		tokenRepo.deleteByUser(user);
	}

	@Override
	public RefreshToken findByUser(int userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new NotFoundResourceException("Not found user"));
		
		return tokenRepo.findByUser(user);
	}

}
