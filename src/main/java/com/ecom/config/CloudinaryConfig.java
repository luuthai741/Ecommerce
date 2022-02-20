package com.ecom.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {
	@Bean
	public Cloudinary cloudinary() {
		Map map = new HashMap<String,String>();
		map.put("cloud_name", "dh5q6zmjf");
		map.put("api_key", "697963862273554");
		map.put("api_secret", "h2bIBI57gp0m2I2zCopOIXKdsMk");
		map.put("secure", true);
		Cloudinary cloudinary = new Cloudinary(map);
		return cloudinary;
	}
}
