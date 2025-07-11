package com.example.spring_security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private record Products(int productId,String productName,double price) {}
	
	List<Products> products=new ArrayList<>(List.of(new Products(1,"samsung",304.2),new Products(2,"nokia",405.6)));
	
	@GetMapping()
	public List<Products> getProduct(){
		return products;
	}
	
	@PostMapping()
	public Products addProduct(@RequestBody Products product) {
		products.add(product);
		return product;
	}

}
