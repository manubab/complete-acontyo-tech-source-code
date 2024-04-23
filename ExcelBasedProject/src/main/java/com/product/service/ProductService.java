package com.product.service;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService
{
	public String readData(MultipartFile file)throws Exception;
	
	

}
