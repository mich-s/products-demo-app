package com.michal.products.repository;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;

import com.michal.products.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
