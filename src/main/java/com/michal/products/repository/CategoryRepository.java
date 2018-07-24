package com.michal.products.repository;

import org.springframework.data.repository.CrudRepository;

import com.michal.products.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
