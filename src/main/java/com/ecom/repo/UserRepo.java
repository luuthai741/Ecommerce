package com.ecom.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	@Query("SELECT u from User u JOIN FETCH u.roles WHERE u.id =?1")
	Optional<User> findByIdAndGetRoles(int id);
	@Query("SELECT u from User u JOIN FETCH u.roles")
	List<User> getUserListWithRoles();
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}