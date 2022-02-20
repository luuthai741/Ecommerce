package com.ecom.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.dto.ProductDTO;
import com.ecom.entities.Product;
import com.ecom.exception.NotFoundResourceException;
import com.ecom.mapper.ProductMapper;
import com.ecom.paging.ProductPaging;
import com.ecom.repo.ProductRepo;
import com.ecom.request.ProductRequest;
import com.ecom.request.UpdateTopProduct;
import com.ecom.service.ProductService;
import com.ecom.utils.UploadUtils;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private UploadUtils uploadUtils;

	@Override
	public List<ProductDTO> findAll() {
		return productMapper.mapProductDTO(productRepo.findAll());
	}

	@Override
	public ProductDTO findByID(int id) {
		Product product = productRepo.findById(id)
				.orElseThrow(() -> new NotFoundResourceException("Cannt found product with id " + id));

		return productMapper.mapToDTO(product);
	}

	@Override
	public ProductDTO save(ProductRequest request, MultipartFile file) {
		Product product = productMapper.mapToEntity(request);
		if (file != null) {
			Map<String, Object> map = uploadUtils.uploadToCloud(file);
			String url = (String) map.get("secure_url");
			String imageName = (String) map.get("asset_id");
			product.setImageName(imageName);
			product.setImageURL(url);

			productRepo.save(product);
			ProductDTO dto = productMapper.mapToDTO(product);
			dto.setCreateTime(new Date());
			return dto;
		}

		return productMapper.mapToDTO(null);
	}

	@Override
	public void deleteByID(int id) {
		productRepo.deleteById(id);
	}

	@Override
	public ProductDTO update(ProductRequest request, MultipartFile file) {
		Product product = productRepo.findById(request.getId())
				.orElseThrow(() -> new NotFoundResourceException("Cannt found product with id " + request.getId()));
		if (product != null) {
			Product productRequest = productMapper.mapToEntity(request);
			if (file == null) {
				productRequest.setImageName(product.getImageName());
				productRequest.setImageURL(product.getImageURL());
				productRepo.save(productRequest);
			} else {
				Map<String, Object> map = uploadUtils.uploadToCloud(file);
				String url = (String) map.get("secure_url");
				String imageName = (String) map.get("asset_id");

				productRequest.setImageName(imageName);
				productRequest.setImageURL(url);

				productRepo.save(productRequest);
			}
			ProductDTO dto = productMapper.mapToDTO(productRequest);
			dto.setCreateTime(product.getCreateTime());
			dto.setUpdateTime(new Date());
			return dto;
		}
		return null;
	}

	@Override
	public List<ProductDTO> topProducts() {
		return productMapper.mapProductDTO(productRepo.topProducts());
	}

	@Override
	public ProductDTO updateStatus(UpdateTopProduct request) {
		Product product = productRepo.findById(request.getId())
				.orElseThrow(() -> new NotFoundResourceException("Cannt found product with id " + request.getId()));
		product.setTop(request.isTopStatus());
		
		productRepo.save(product);
		
		return productMapper.mapToDTO(product);
	}

	private Page<Product> pageable(Pageable pageable){
		return productRepo.findAll(pageable);
	}
	@Override
	public ProductPaging paging(String keyword,int page, int limit, String sortBy, String sortDir) {
		Sort sort =null;
		if(sortDir.equalsIgnoreCase("desc")) {
			sort = Sort.by(Direction.DESC, sortBy);
		}
		else if(!sortDir.equalsIgnoreCase("desc")) {
			sort = Sort.by(Direction.ASC,sortBy);
		}
		Pageable pageable = PageRequest.of(page-1, limit,sort);
		Page<Product> productPage = null;
		if(keyword.length() == 0 || keyword.isBlank() ) {
			productPage = this.pageable(pageable);
		}else {
			productPage = productRepo.search(pageable,keyword);
		}
		ProductPaging productPaging = new ProductPaging();
		productPaging.setProductsList(productMapper.mapProductDTO(productPage.getContent()));
		productPaging.setPages(productPage.getTotalPages());
		productPaging.setCurrentPage(page);
		return productPaging;
	}
}
