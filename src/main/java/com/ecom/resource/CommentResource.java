package com.ecom.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.CommentDTO;
import com.ecom.request.CommentRequest;
import com.ecom.service.CommentService;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentResource {
	@Autowired
	private CommentService commentService;


	@GetMapping("/{productId}")
	public ResponseEntity<?> findByProduct(@PathVariable int productId) {
		List<CommentDTO> dtoList = commentService.findAllByProduct(productId);
		return ResponseEntity.ok().body(dtoList);
	}

	@PreAuthorize("hasRole('USER')")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody CommentRequest request) {
		CommentDTO dto = commentService.save(request);
		if (dto != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		}
		return ResponseEntity.badRequest().body("Cannot create comment");
	}
}
