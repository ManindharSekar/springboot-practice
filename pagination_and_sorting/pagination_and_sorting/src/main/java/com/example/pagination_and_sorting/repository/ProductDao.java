package com.example.pagination_and_sorting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pagination_and_sorting.entity.Products;
@Repository
public interface ProductDao extends JpaRepository<Products, Integer>{

}
