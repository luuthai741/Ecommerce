package com.ecom.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
	private int id;
	private String name;
	private String slug;
	private String imageName;
	private String imageURL;
	private String description;
	private long price;
	private int quantity;
	private boolean status;
	private boolean isTop;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm:ss yyyy-MM-dd")
	private Date createTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm:ss yyyy-MM-dd")
	private Date updateTime;
}
