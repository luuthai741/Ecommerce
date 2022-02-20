package com.ecom.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entities.User;
import com.ecom.entities.UserInfo;

@Repository
public interface UserInfoRepo extends CrudRepository<UserInfo, Integer>  {
	public UserInfo findByUser(User user);
}
