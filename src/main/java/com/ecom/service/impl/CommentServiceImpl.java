package com.ecom.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dto.CommentDTO;
import com.ecom.entities.Comment;
import com.ecom.entities.Product;
import com.ecom.exception.NotFoundResourceException;
import com.ecom.mapper.CommentMapper;
import com.ecom.repo.CommentRepo;
import com.ecom.repo.ProductRepo;
import com.ecom.request.CommentRequest;
import com.ecom.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private CommentRepo commentRepo;

	@Override
	public CommentDTO save(CommentRequest request) {
		Product product = productRepo.findById(request.getProductId()).orElseThrow(
				() -> new NotFoundResourceException("Cannt found product with id " + request.getProductId()));
		Comment comment = commentMapper.mapToEntity(request);
		comment.setProduct(product);
		comment.setCreateTime(new Date());
		commentRepo.save(comment);

		return commentMapper.mapToDTO(comment);
	}

	@Override
	public List<CommentDTO> findAllByProduct(int productId) {
		Product product = productRepo.findById(productId).orElseThrow(
				() -> new NotFoundResourceException("Cannt found product with id " + productId));
		List<Comment> comments = commentRepo.findByProduct(product);

		return commentMapper.mapToDTO(comments);
	}

	@Override
	public List<CommentDTO> findAll() {
		return commentMapper.mapToDTO(commentRepo.findAll());
	}

}
