package com.ecom.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entities.RefreshToken;
import com.ecom.entities.User;

@Repository
public interface RefreshTokenRepo extends CrudRepository<RefreshToken, Integer> {
	Optional<RefreshToken> findByToken(String token);
	RefreshToken findByUser(User user);
	@Query("SELECT t from RefreshToken t JOIN FETCH t.user")
	RefreshToken joinUser(int id);
	void deleteByUser(User user);
}
