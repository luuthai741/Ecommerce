package com.ecom.service.impl;

import javax.security.auth.login.AccountNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudinary.api.exceptions.NotFound;
import com.ecom.dto.UserInfoDTO;
import com.ecom.entities.User;
import com.ecom.entities.UserInfo;
import com.ecom.exception.NotFoundResourceException;
import com.ecom.mapper.UserInfoMapper;
import com.ecom.repo.UserInfoRepo;
import com.ecom.repo.UserRepo;
import com.ecom.request.UserInfoRequest;
import com.ecom.service.UserInfoService;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserInfoRepo infoRepo;
	@Autowired
	private UserInfoMapper infoMapper;

	@Override
	public UserInfoDTO save(UserInfoRequest request) {
		User user = userRepo.findById(request.getUserId())
				.orElseThrow(() -> new NotFoundResourceException("Not found user with id " + request.getUserId()));
		UserInfo savedInfo = infoRepo.findByUser(user);
		if (savedInfo != null) {
			savedInfo.setAddress(request.getAddress());
			savedInfo.setPhonenumber(request.getPhonenumber());
			savedInfo.setFirstname(request.getFirstname());
			savedInfo.setLastname(request.getLastname());

			infoRepo.save(savedInfo);
			return infoMapper.mapToDTO(savedInfo);
		} else {
			UserInfo info = infoMapper.mapToEntity(request);
			info.setUser(user);

			infoRepo.save(info);
			return infoMapper.mapToDTO(info);
		}
	}

	@Override
	public UserInfoDTO findByUserId(int id) {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new NotFoundResourceException("Not found user with id" + id));
		UserInfo info = infoRepo.findByUser(user);
		if (info != null) {
			return infoMapper.mapToDTO(info);
		}
		return null;
	}

}
