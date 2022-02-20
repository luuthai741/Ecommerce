package com.ecom.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserResource {
	@Autowired
	private UserService userService;
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(userService.getUserListWithRoles());
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		userService.deteleByID(id);
		return ResponseEntity.ok("Xoá người dùng thành công");
	}
}
