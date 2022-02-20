package com.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
	private int id;
	private int productId;
	private int orderId;
	private String productName;
	private String imageURL;
	private String imageName;
	private int quantity;
	private long itemPrice;
	private long total;
}
