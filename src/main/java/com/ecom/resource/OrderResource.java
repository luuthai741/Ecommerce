package com.ecom.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.OrderDTO;
import com.ecom.request.OrderRequest;
import com.ecom.request.OrderUpdateRequest;
import com.ecom.service.OrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderResource {
	@Autowired
	private OrderService orderService;
	@PreAuthorize("hasRole('USER')")
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createOrder(@RequestBody OrderRequest request){
		OrderDTO dto = orderService.createOrder(request);
		if(dto != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		}
		return ResponseEntity.badRequest().body("Lỗi không thể tạo đơn đặt hàng. Xin vui lòng thử lại sau");
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public ResponseEntity<?> finAll(@RequestParam(required = false, defaultValue = "") String keyword, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "4") int limit, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDir){
		return ResponseEntity.ok().body(orderService.paging(keyword, page, limit, sortBy, sortDir));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{orderId}")
	public ResponseEntity<?> deleteByID(@PathVariable int orderId){
		OrderDTO dto = orderService.deletById(orderId);
		if(dto != null) {
			return ResponseEntity.ok().body("Xoá đơn hàng thành công");
		}
		return ResponseEntity.badRequest().body("Something error");
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity<?> updateStatus(@RequestBody OrderUpdateRequest request){
		OrderDTO dto = orderService.updateStatus(request.getOrderId(), request.getStatus());
		if(dto != null) {
			return ResponseEntity.ok().body(dto);
		}
		return ResponseEntity.badRequest().body("Something error");
	}
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{orderId}")
	public ResponseEntity<?> findById(@PathVariable int orderId){
		OrderDTO dto = orderService.findById(orderId);
		if(dto != null) {
			return ResponseEntity.ok().body(dto);
		}
		return ResponseEntity.badRequest().body("Something error");
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<?> findByUserId(@RequestParam int userId){
		List<OrderDTO> dtoList = orderService.findByUserId(userId);
		return ResponseEntity.ok().body(dtoList);
	}
}
