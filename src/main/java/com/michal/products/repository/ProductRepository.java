package com.michal.products.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;

import com.michal.products.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

//	List<Test> findByUsers_UserName(String userName);
	
	List<Product> findByCategories_Id(Long id);
	
	
	
	
}
