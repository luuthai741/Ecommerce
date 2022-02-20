package com.ecom.paging;

import java.util.List;

import com.ecom.dto.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPaging {
	private List<ProductDTO> productsList;
	private int currentPage;
	private int pages;
}
