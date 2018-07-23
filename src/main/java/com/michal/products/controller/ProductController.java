package com.michal.products.controller;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.michal.products.entity.Product;
import com.michal.products.repository.ProductRepository;

@Controller
public class ProductController {

	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/products")
	public String showHome(Model model) {
//		ArrayList<Product> products = (ArrayList<Product>) productRepository.findAll();
//		System.out.println(products);
		model.addAttribute("products", productRepository.findAll());
		return "products";
	}
	
	@GetMapping("/products/addProduct")
	public String addProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "products-form";
	}
	
	@PostMapping("/products/processProductForm")
	public String processProductForm(@ModelAttribute("product") Product product) {
		productRepository.save(product);
		return "redirect:/products";
	}
	
	@GetMapping("/products/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productRepository.deleteById(id);
		return "redirect:/products";
	}
	
	@GetMapping("/products/update/{id}")
	public String updateProduct(@PathVariable Long id, Model model) {
		myLogger.info("Product " + productRepository.findById(id).get());
		model.addAttribute("product", productRepository.findById(id).get());
		return "products-form";
	}
}
