package com.ecom.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.multipart.MultipartFile;

import com.ecom.dto.ProductDTO;
import com.ecom.repo.ProductRepo;
import com.ecom.request.ProductRequest;
import com.ecom.request.UpdateTopProduct;
import com.ecom.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductResource {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRepo productRepo;
//	@GetMapping
//	public ResponseEntity<?> findAll() {
//		return ResponseEntity.ok(productService.findAll());
//	}
	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(required = false, defaultValue = "") String keyword, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "8") int limit, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDir) {
		return ResponseEntity.ok(productService.paging(keyword, page, limit, sortBy, sortDir));
	}
	@GetMapping("/top")
	public ResponseEntity<?> topProducts() {
		return ResponseEntity.ok(productService.topProducts());
	}
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id") int id) {
		return ResponseEntity.ok(productService.findByID(id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> save(@ModelAttribute ProductRequest request, MultipartFile file) {
		ProductDTO dto = productService.save(request, file);
		if (dto != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		}
		return ResponseEntity.badRequest().body("Có lỗi gì đó xảy ra. Không thể tạo sản phẩm");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity<?> update(@ModelAttribute ProductRequest request, MultipartFile file) {
		ProductDTO dto = productService.update(request, file);
		if (dto != null) {
			return ResponseEntity.ok(dto);
		}
		return ResponseEntity.badRequest().body("Có lỗi gì đó xảy ra. Không thể cập nhật sản phẩm");
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/top")
	public ResponseEntity<?> updateTopProduct(@RequestBody UpdateTopProduct request) {
		ProductDTO dto = productService.updateStatus(request);
		if (dto != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		}
		return ResponseEntity.badRequest().body("Có lỗi gì đó xảy ra. Không thể tạo sản phẩm");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteByID(@PathVariable(name = "id") int id) {
		productService.deleteByID(id);
		return ResponseEntity.ok("Xoá sản phẩm thành công !");
	}
}
