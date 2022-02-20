package com.ecom.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecom.dto.ProductDTO;
import com.ecom.paging.ProductPaging;
import com.ecom.request.ProductRequest;
import com.ecom.request.UpdateTopProduct;

public interface ProductService {
	public List<ProductDTO> findAll();
	public List<ProductDTO> topProducts();
	public ProductPaging paging(String keyword,int page, int limit, String sortBy, String sortDir);
	public ProductDTO findByID(int id);
	public ProductDTO updateStatus(UpdateTopProduct request);
	public ProductDTO save(ProductRequest request, MultipartFile file);
	public ProductDTO update(ProductRequest request, MultipartFile file);
	public void deleteByID(int id);
}
