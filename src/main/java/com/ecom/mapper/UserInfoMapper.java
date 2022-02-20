package com.ecom.mapper;

import org.springframework.stereotype.Component;

import com.ecom.dto.UserInfoDTO;
import com.ecom.entities.UserInfo;
import com.ecom.request.UserInfoRequest;

@Component
public class UserInfoMapper {
	public UserInfo mapToEntity(UserInfoRequest request) {
		UserInfo info = new UserInfo();
		info.setAddress(request.getAddress());
		info.setPhonenumber(request.getPhonenumber());
		info.setFirstname(request.getFirstname());
		info.setLastname(request.getLastname());
		
		return info;
	}
	public UserInfoDTO mapToDTO(UserInfo info) {
		UserInfoDTO dto = new UserInfoDTO();
		dto.setId(info.getId());
		dto.setAddress(info.getAddress());
		dto.setFirstname(info.getFirstname());
		dto.setLastname(info.getLastname());
		dto.setPhonenumber(info.getPhonenumber());
		
		return dto;
	}
	
}

