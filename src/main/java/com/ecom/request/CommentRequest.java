package com.ecom.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
	private long id;
	private int userId;
	private String username;
	private int productId;
	private String content;
	private float rating;
}
