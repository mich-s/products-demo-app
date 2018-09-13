package com.michal.products;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.michal.products.entity.Category;
import com.michal.products.entity.Product;
import com.michal.products.repository.CategoryRepository;
import com.michal.products.repository.ProductRepository;

@SpringBootApplication
public class ProductsApplication implements CommandLineRunner{

	/*@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;*/
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}
	
	@Override
	public void run(String... strings) throws Exception{
		
		/*List<Product> products = (List<Product>) productRepository.findAll();
//		List<Category> categories = (List<Category>) categoryRepository.findAll();
//		Iterable<Product> products = productRepository.findAll();
		System.out.println(">>>>>>>>>>>>>>>>" + products);
		if(products.isEmpty()) {
			Product coffee_1 = new Product();
			coffee_1.setName("Cappuccino");
			coffee_1.setPrice(6.00);
			
			Product coffee_2 = new Product();
			coffee_2.setName("Latte");
			coffee_2.setPrice(6.50);
			
			
			productRepository.save(coffee_1);
			productRepository.save(coffee_2);
			
		}
		Category category1 = new Category();
//		Category category2 = new Category();
		category1.setName("abcaz");
//		category2.setName("nameee");
		
		categoryRepository.save(category1);
//		categoryRepository.save(category2);
		
		myLogger.info("Category 1" + category1);
//		myLogger.info("Category 2" + category1);
		
		List<Category> categories = new ArrayList<>();
		categories.add(category1);
//		categories.add(category2);
		
		myLogger.info("Categories" + categories);
		
		Product coffee = new Product();
		coffee.setName("Mocha");
		coffee.setPrice(6.50);
		
		myLogger.info("Product " + coffee);
		coffee.setCategories(categories);
		
		myLogger.info("Product " + coffee);
		
		productRepository.save(coffee);*/
		
		/*
		Category category = new Category();
		category.setName("teslaaa");
		categoryRepository.save(category);*/
		
	}
	
	
	
}
