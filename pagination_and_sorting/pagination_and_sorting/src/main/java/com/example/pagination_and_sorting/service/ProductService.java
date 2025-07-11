package com.example.pagination_and_sorting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.example.pagination_and_sorting.entity.Products;
import com.example.pagination_and_sorting.repository.ProductDao;

@Service
public class ProductService {
	@Autowired
	ProductDao productDao;

	public List<Products> getAllProducts() {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}

	public List<Products> getSortedProducts(String field) {
		// TODO Auto-generated method stub
		
		System.out.println(field);
		return productDao.findAll(Sort.by(Sort.Direction.ASC,field));
	}

	public Page<Products> getWithPagination(int offset, int size) {
		// TODO Auto-generated method stub
		return productDao.findAll(PageRequest.of(offset, size));
	}

	public Page<Products> getWithPaginationAndSorting(int offset, int size, String field) {
		// TODO Auto-generated method stub
		return productDao.findAll(PageRequest.of(offset, size).withSort(Sort.Direction.ASC,field));
	}

}
