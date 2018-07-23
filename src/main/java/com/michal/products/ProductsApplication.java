package com.michal.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.michal.products.entity.Product;
import com.michal.products.repository.ProductRepository;

@SpringBootApplication
public class ProductsApplication implements CommandLineRunner{

	@Autowired
	ProductRepository productRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}
	
	@Override
	public void run(String... strings) throws Exception{
		
		List<Product> products = (List<Product>) productRepository.findAll();
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
		
	}
	
	
	
}
