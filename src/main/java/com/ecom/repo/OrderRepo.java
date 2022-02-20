package com.ecom.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.entities.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
	public List<Order> findByUserId(int userId);
	@Query("SELECT o from Order o WHERE o.username LIKE %:keyword%")
	public Page<Order> search(String keyword,Pageable pageable);
	public List<Order> findAll();
}
