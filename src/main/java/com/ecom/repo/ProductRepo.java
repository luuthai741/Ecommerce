package com.ecom.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.entities.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
	@Query("SELECT p FROM Product p WHERE p.isTop = 1")
	List<Product> topProducts();
	
	@Query("SELECT p from Product p WHERE p.name LIKE %:keyword%")
	Page<Product> search(Pageable pageable, @Param("keyword")String keyword);
	
	@Query("SELECT p from Product p JOIN FETCH p.comments")
	List<Product> joinComment();
}
