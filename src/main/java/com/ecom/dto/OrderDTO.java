package com.ecom.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	private int id;
	private String username;
	private long shippingCost;
	private String address;
	private String  paymentMethod;
	private long total;
	private String phonenumber;
	private List<CartItemDTO> cartItems;
	private Date createTime;
	private int status;
}
