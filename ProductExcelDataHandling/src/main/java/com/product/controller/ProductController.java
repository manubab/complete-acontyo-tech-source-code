package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.product.entity.Product;
import com.product.service.ProductServiceImpl;

@RestController
@RequestMapping("/product")
public class ProductController 
{
	@Autowired
	ProductServiceImpl productService;
	@PostMapping("/upload")
	public List<Product> readFileDataAndSegregateDataBasedOnCondition(@RequestParam("file") MultipartFile file) throws Exception {
		
		List<Product> listOfProducts=productService.readFileDataAndSegregateDataBasedOnCondition(file);
		return listOfProducts ;
	}

}
