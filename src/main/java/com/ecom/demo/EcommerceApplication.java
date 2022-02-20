package com.ecom.demo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecom.entities.Product;
import com.ecom.entities.Role;
import com.ecom.entities.User;
import com.ecom.repo.ProductRepo;
import com.ecom.repo.RoleRepo;
import com.ecom.repo.UserRepo;

@Configuration
@SpringBootApplication(scanBasePackages = { "com.ecom" })
@EntityScan(basePackages = {"com.ecom.entities"})
@EnableJpaRepositories(basePackages = {"com.ecom.repo"})
public class EcommerceApplication {
	@Autowired
	private UserRepo userRepo; 
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private PasswordEncoder encoder;
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
	
//	@PostConstruct
//	void init() {
//		List<User> users = userRepo.findAll();
//		List<Role> roles = roleRepo.findAll();
//		if(roles.isEmpty()) {
//			roleRepo.save(new Role(0, "ROLE_USER"));
//			roleRepo.save(new Role(0, "ROLE_ADMIN"));
//		}
//		if(users.isEmpty()) {
//			User user = new User();
//			user.setEnabled(true);
//			user.setPassword(encoder.encode("adminpassword"));
//			user.setUsername("admin");
//			user.setEmail("luuthai555@gmail.com");
//			user.setPhonenumber("0888400553");
//			user.setAddress("Bắc Ninh");
//			
//			Set<Role> role = new HashSet<>();
//			Role adminRole = roleRepo.findByName("ROLE_ADMIN");
//			role.add(adminRole);
//			user.setRoles(role);
//			userRepo.save(user);
//		}
//	}
}
