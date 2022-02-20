package com.ecom.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.UserInfoDTO;
import com.ecom.request.UserInfoRequest;
import com.ecom.service.UserInfoService;

@RestController
@RequestMapping("/api/v1/info")
@PreAuthorize("hasRole('USER')")
public class UserInfoResource {
	@Autowired
	private UserInfoService infoService;
	@GetMapping("/{userId}")
	public ResponseEntity<?> findByUserId(@PathVariable int userId){
		UserInfoDTO dto = infoService.findByUserId(userId);
		if(dto != null) {
			return ResponseEntity.ok(dto);
		}
		else {
			return ResponseEntity.ok(null);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody UserInfoRequest request){
		UserInfoDTO dto = infoService.save(request);
		if(dto != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		}
		return ResponseEntity.badRequest().body("Cannt create userinfo");
	}
}
