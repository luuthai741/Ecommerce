package com.ecom.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ecom.dto.CommentDTO;
import com.ecom.entities.Comment;
import com.ecom.request.CommentRequest;

@Component
public class CommentMapper {
	public CommentDTO mapToDTO(Comment comment) {
		CommentDTO dto = new CommentDTO();
		dto.setId(comment.getId());
		dto.setProductId(comment.getProduct().getId());
		dto.setUserId(comment.getUserId());
		dto.setUsername(comment.getUsername());
		dto.setRating(comment.getRating());
		dto.setComment(comment.getContent());
		dto.setCreateTime(comment.getCreateTime());
		dto.setUpdateTime(comment.getUpdateTime());
		
		return dto;
	}
	
	public List<CommentDTO> mapToDTO(List<Comment> comments) {
		return comments.stream().map(x -> this.mapToDTO(x)).collect(Collectors.toList());
	} 
	
	public Comment mapToEntity(CommentRequest request) {
		Comment comment = new Comment();
		comment.setId(request.getId());
		comment.setRating(request.getRating());
		comment.setContent(request.getContent());
		comment.setUserId(request.getUserId());
		comment.setUsername(request.getUsername());
		
		return comment;
	}
}
