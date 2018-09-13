package com.michal.products.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.michal.products.entity.Category;
import com.michal.products.entity.Product;
import com.michal.products.repository.CategoryRepository;
import com.michal.products.repository.ProductRepository;

@Service
public class ProductsServiceImpl implements ProductsService {

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	
	public ProductsServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}
	
	@Override
	public List<Product> findAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long id) {
		return productRepository.findById(id).get();
	}

	@Override
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public List<Product> findProductsByCategories_Id(Long id) {
		return productRepository.findByCategories_Id(id);
	}

	@Override
	public List<Category> findAllCategories() {
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category findCategoryById(Long id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public void deleteCategory(Category category) {
		categoryRepository.delete(category);
	}

}
