package com.ecom.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
	private int userId;
	private String username;
	private String address;
	private String phonenumber;
	private long shippingCost;
	private String paymentMethod;
	private long total;
	private List<CartItemRequest> cartItems;
}
