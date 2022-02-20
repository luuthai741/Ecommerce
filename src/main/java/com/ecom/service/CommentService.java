package com.ecom.service;

import java.util.List;

import com.ecom.dto.CommentDTO;
import com.ecom.request.CommentRequest;

public interface CommentService {
	public CommentDTO save(CommentRequest request);
	public List<CommentDTO> findAllByProduct(int productId);
	public List<CommentDTO> findAll();
}
