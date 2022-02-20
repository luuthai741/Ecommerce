package com.ecom.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ecom.dto.CartItemDTO;
import com.ecom.dto.OrderDTO;
import com.ecom.entities.CartItem;
import com.ecom.entities.Order;
import com.ecom.entities.Product;
import com.ecom.exception.NotFoundResourceException;
import com.ecom.mapper.CartItemMapper;
import com.ecom.mapper.OrderMapper;
import com.ecom.paging.OrderPaging;
import com.ecom.paging.ProductPaging;
import com.ecom.repo.CartItemRepo;
import com.ecom.repo.OrderRepo;
import com.ecom.repo.ProductRepo;
import com.ecom.request.CartItemRequest;
import com.ecom.request.OrderRequest;
import com.ecom.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private CartItemRepo itemRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private CartItemMapper itemMapper;
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public OrderDTO createOrder(OrderRequest request) {
			Order order = orderMapper.mapToEntity(request);	
			orderRepo.save(order);
			for(CartItemRequest itemRequest : request.getCartItems()) {
				Product product = productRepo.findById(itemRequest.getProductId()).orElseThrow(()-> new NotFoundResourceException("Not found product with id " + itemRequest.getProductId()));
				CartItem item = itemMapper.mapToEntity(itemRequest);
				item.setImageName(product.getImageName());
				item.setImageURL(product.getImageURL());		
				item.setOrderId(order.getId());
				itemRepo.save(item);
				
				product.setQuantity(product.getQuantity() - item.getQuantity());
				if(product.getQuantity() <=0) {
					product.setStatus(false);
				}
				productRepo.save(product);
			}
		
			return orderMapper.mapToDTO(order);
	}
	@Override
	public List<OrderDTO> findByUserId(int id) {
		List<Order> orders = orderRepo.findByUserId(id);
		return orderMapper.mapToDTO(orders);
	}
	@Override
	public OrderDTO findById(int id) {
		Order order = orderRepo.findById(id).orElseThrow(()-> new NotFoundResourceException("Not found order with id " + id ));
		List<CartItem> items = itemRepo.findByOrderId(id);
		List<CartItemDTO> itemsDTO = itemMapper.mapToDTO(items);
		OrderDTO orderDTO = orderMapper.mapToDTO(order);
		orderDTO.setCartItems(itemsDTO);
		return orderDTO;
	}
	@Override
	public List<OrderDTO> findAll() {
		return orderMapper.mapToDTO(orderRepo.findAll());
	}
	@Override
	public OrderDTO deletById(int id) {
		Order order = orderRepo.findById(id).orElseThrow(()-> new NotFoundResourceException("Not found order with id " + id ));
		OrderDTO dto = orderMapper.mapToDTO(order);
		List<CartItem> items = itemRepo.findByOrderId(order.getId());
		for(CartItem item : items) {
			itemRepo.deleteById(item.getId());
		}
		orderRepo.deleteById(id);
		return dto;
	}
	@Override
	public OrderDTO updateStatus(int orderId, int status) {
		Order order = orderRepo.findById(orderId).orElseThrow(()-> new NotFoundResourceException("Not found order with id " + orderId ));
		order.setStatus(status);
		return orderMapper.mapToDTO(order);
	}
	private Page<Order> pageable(Pageable pageable){
		return orderRepo.findAll(pageable);
	}
	@Override
	public OrderPaging paging(String keyword, int page, int limit, String sortBy, String sortDir) {
		Sort sort =null;
		if(sortDir.equalsIgnoreCase("desc")) {
			sort = Sort.by(Direction.DESC, sortBy);
		}
		else if(!sortDir.equalsIgnoreCase("desc")) {
			sort = Sort.by(Direction.ASC,sortBy);
		}
		Pageable pageable = PageRequest.of(page-1, limit,sort);
		Page<Order> orderPage = null;
		if(keyword.length() == 0 || keyword.isBlank() ) {
			orderPage = this.pageable(pageable);
		}else {
			orderPage = orderRepo.search(keyword,pageable);
		}
		OrderPaging orderPaging = new OrderPaging();
		orderPaging.setOrders(orderMapper.mapToDTO(orderPage.getContent()));
		orderPaging.setPages(orderPage.getTotalPages());
		orderPaging.setCurrentPage(page);
		return orderPaging;
	}

}
