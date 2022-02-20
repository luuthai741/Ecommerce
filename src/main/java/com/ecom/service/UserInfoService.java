package com.ecom.service;

import com.ecom.dto.UserInfoDTO;
import com.ecom.request.UserInfoRequest;

public interface UserInfoService {
	public UserInfoDTO save(UserInfoRequest request);
	public UserInfoDTO findByUserId(int id);
}
