package com.ecom.paging;

import java.util.List;

import com.ecom.dto.OrderDTO;
import com.ecom.dto.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaging {
	private List<OrderDTO> orders;
	private int currentPage;
	private int pages;
}
