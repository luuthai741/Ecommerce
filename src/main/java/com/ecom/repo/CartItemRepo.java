package com.ecom.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entities.CartItem;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
	public List<CartItem> findByOrderId(int orderId);
}
