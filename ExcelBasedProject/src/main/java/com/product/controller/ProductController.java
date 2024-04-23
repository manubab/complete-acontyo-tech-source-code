package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.product.service.ProductService;

@RestController
public class ProductController
{
	@Autowired
	ProductService service;
	
	@PostMapping("/readFile")
	public String readData(@RequestParam("file") MultipartFile file) throws Exception {
	String data=service.readData(file);
		return data;
	}
	

}
