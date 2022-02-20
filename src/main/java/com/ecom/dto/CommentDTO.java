package com.ecom.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
	private long id;
	private int productId;
	private int userId;
	private String username;
	private String comment;
	private float rating;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm:ss yyyy-MM-dd")
	private Date createTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm:ss yyyy-MM-dd")
	private Date updateTime;
}
