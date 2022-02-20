package com.ecom.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ecom.dto.CartItemDTO;
import com.ecom.entities.CartItem;
import com.ecom.request.CartItemRequest;

@Component
public class CartItemMapper {
	public CartItem mapToEntity(CartItemRequest request) {
		CartItem item = new CartItem();
		item.setProductId(request.getProductId());
		item.setProductName(request.getProductName());
		item.setQuantity(request.getQuatity());
		item.setItemPrice(request.getItemPrice());
		item.setTotal(request.getTotal());	
		
		return item;
	}
	public CartItemDTO mapToDTO(CartItem item) {
		CartItemDTO dto = new CartItemDTO();
		dto.setId(item.getId());
		dto.setImageName(item.getImageName());
		dto.setImageURL(item.getImageURL());
		dto.setItemPrice(item.getItemPrice());
		dto.setProductId(item.getProductId());
		dto.setProductName(item.getProductName());
		dto.setQuantity(item.getQuantity());
		dto.setTotal(item.getTotal());
		
		return dto;
	}
	public List<CartItemDTO> mapToDTO(List<CartItem> items) {
		return items.stream().map(x -> this.mapToDTO(x)).collect(Collectors.toList());
	}
}
