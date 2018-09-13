package com.michal.products.service;

import java.util.List;

import com.michal.products.entity.Category;
import com.michal.products.entity.Product;

public interface ProductsService {

	List<Product> findAllProducts();
	Product saveProduct(Product product);
	Product findProductById(Long id);
	void deleteProductById(Long id);
	
	List<Product> findProductsByCategories_Id(Long id);
	
	List<Category> findAllCategories();
	Category saveCategory(Category category);
	Category findCategoryById(Long id);
	void deleteCategory(Category category);
}
