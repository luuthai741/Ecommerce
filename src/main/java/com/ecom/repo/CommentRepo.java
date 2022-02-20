package com.ecom.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entities.Comment;
import com.ecom.entities.Product;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
	public List<Comment> findByProduct(Product product);
	public List<Comment> findAll();
}
