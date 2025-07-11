package com.example.pagination_and_sorting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pagination_and_sorting.entity.Products;
import com.example.pagination_and_sorting.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@GetMapping("allProducts")
	public List<Products> getAllProducts(){
		return productService.getAllProducts();
		
	}
	
	@GetMapping("sort/{field}")
	public List<Products> getSortedProducts(@PathVariable String field){
		
		 return productService.getSortedProducts(field);
	}
	
	@GetMapping("pagination/{offset}/{size}")
	public Page<Products> getPaginationProduct(@PathVariable int offset,@PathVariable int size){
		return productService.getWithPagination(offset,size);
	}
	
	
	@GetMapping("paginationAndSorting/{offset}/{size}/{field}")
	public Page<Products> getPaginationAndSortingProduct(@PathVariable int offset,@PathVariable int size,@PathVariable String field)
	{
		return productService.getWithPaginationAndSorting(offset, size,field);
	}

}
