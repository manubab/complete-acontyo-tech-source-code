package com.inventory.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerUIController {

	  @GetMapping("/api-docs")
	    public String swaggerUI() {
	        return "redirect:/swagger-ui/index.html";
	    }
}
