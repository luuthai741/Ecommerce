package com.ecom.service;

import java.util.List;

import com.ecom.dto.CartItemDTO;
import com.ecom.dto.OrderDTO;
import com.ecom.entities.CartItem;
import com.ecom.paging.OrderPaging;
import com.ecom.paging.ProductPaging;
import com.ecom.request.OrderRequest;

public interface OrderService {
	public OrderDTO createOrder(OrderRequest orderRequest);
	public OrderPaging paging(String keyword,int page, int limit, String sortBy, String sortDir);
	public List<OrderDTO> findByUserId(int id);
	public List<OrderDTO> findAll();
	public OrderDTO findById(int id);
	public OrderDTO deletById(int id);
	public OrderDTO updateStatus(int orderId,int status);
}
