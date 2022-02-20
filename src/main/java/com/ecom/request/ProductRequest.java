package com.ecom.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
	private int id;
	private String name;
	private String description;
	private long price;
	private int quantity;
	private boolean status;
}
