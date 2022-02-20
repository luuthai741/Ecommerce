package com.ecom.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {
	private int productId;
	private String productName;
	private int quatity;
	private long itemPrice;
	private long total;
}
