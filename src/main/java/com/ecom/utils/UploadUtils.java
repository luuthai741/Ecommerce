package com.ecom.utils;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Component
public class UploadUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadUtils.class);
	@Autowired
	private Cloudinary cloudinary;
	
	public Map<String,Object> uploadToCloud(MultipartFile multipartFile){
		Map<String, Object> cloudinaryUrl = null;
		Map params = ObjectUtils.asMap("resource_type", "auto");	
	try {
		@SuppressWarnings("unchecked")
		Map<String, Object> result = cloudinary.uploader().upload(multipartFile.getBytes(), params);
		cloudinaryUrl= result;
		logger.info("link image {}",result.get("secure_url"));
	} catch (IOException e) {
		System.out.println("Could not upload file to Cloundinary from MultipartFile " + multipartFile.getOriginalFilename()+ e.toString());
	}
		return cloudinaryUrl;
	}
}
