package com.ecom.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecom.dto.ProductDTO;
import com.ecom.entities.Product;
import com.ecom.request.ProductRequest;
import com.ecom.utils.SlugUtils;

@Component
public class ProductMapper {
	
	public ProductDTO mapToDTO(Product product) {
		ProductDTO dto = new ProductDTO();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setSlug(product.getSlug());
		dto.setImageName(product.getImageName());
		dto.setImageURL(product.getImageURL());
		dto.setPrice(product.getPrice());
		dto.setDescription(product.getDescription());
		dto.setQuantity(product.getQuantity());		
		dto.setStatus(product.isStatus());
		dto.setTop(product.isTop());
		dto.setCreateTime(product.getCreateTime());
		dto.setUpdateTime(product.getUpdateTime());
		
		return dto;
	}
	
	public List<ProductDTO> mapProductDTO(List<Product> products){
		return products.stream().map(product -> mapToDTO(product)).collect(Collectors.toList());
	}
	
	public Product mapToEntity(ProductRequest request) {
		Product product = new Product();
		product.setId(request.getId());
		product.setName(request.getName());
		product.setSlug(SlugUtils.makeSlug(product.getName()));
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
		product.setQuantity(request.getQuantity());
		product.setStatus(request.isStatus());
		
		return product;
	}
}
