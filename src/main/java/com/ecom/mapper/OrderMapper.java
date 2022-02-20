package com.ecom.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ecom.dto.OrderDTO;
import com.ecom.entities.Order;
import com.ecom.request.OrderRequest;

@Component
public class OrderMapper {
	public Order mapToEntity(OrderRequest request) {
		Order order = new Order();
		order.setUserId(request.getUserId());
		order.setUsername(request.getUsername());
		order.setAddress(request.getAddress());
		order.setPaymentMethod(request.getPaymentMethod());
		order.setPhonenumber(request.getPhonenumber());
		order.setShippingCost(request.getShippingCost());
		order.setTotal(request.getTotal());
		
		return order;
	}
	
	public OrderDTO mapToDTO(Order order) {
		OrderDTO dto = new OrderDTO();
		dto.setId(order.getId());
		dto.setAddress(order.getAddress());
		dto.setPhonenumber(order.getPhonenumber());
		dto.setPaymentMethod(order.getPaymentMethod());
		dto.setTotal(order.getTotal());
		dto.setUsername(order.getUsername());
		dto.setCreateTime(order.getCreateTime());
		dto.setShippingCost(order.getShippingCost());
		dto.setStatus(order.getStatus());
		return dto;
	}
	
	public List<OrderDTO> mapToDTO(List<Order> orders){
		return orders.stream().map(x -> this.mapToDTO(x)).collect(Collectors.toList());
	}
}
