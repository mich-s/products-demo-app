package com.michal.products.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.michal.products.entity.Category;
import com.michal.products.entity.Product;
import com.michal.products.repository.CategoryRepository;
import com.michal.products.repository.ProductRepository;

@Controller
public class ProductController {

	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	
	@GetMapping("/products")
	public String showHome(Model model) {
//		ArrayList<Product> products = (ArrayList<Product>) productRepository.findAll();
//		System.out.println(products);
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("categories", categoryRepository.findAll());
		return "products";
	}
	
	@GetMapping("/products/addProduct")
	public String addProductForm(Model model) {
		model.addAttribute("product", new Product());
		List<Category> categories = (List<Category>) categoryRepository.findAll();
//		myLogger.info("--------------Categories: " + categories);
		model.addAttribute("categories", categories);
		return "products-form";
	}
	
	@PostMapping("/products/processProductForm")
	public String processProductForm(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
//		Optional<Category> categories = categoryRepository.findById(categoryId); 
//		Category category = categories.get();
//		myLogger.info(".........Category - optional: " + categories);
//		myLogger.info(".........Category: " + category);
//		myLogger.info("products categories " + product.getCategories());
//		product.getCategories().add(category);
		if(bindingResult.hasErrors()) {
			List<Category> categories = (List<Category>) categoryRepository.findAll();
			model.addAttribute("categories", categories);
			return "products-form";
		} else {
			productRepository.save(product);
			return "redirect:/products";
		}
		
	}
	
	@GetMapping("/products/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productRepository.deleteById(id);
		return "redirect:/products";
	}
	
	@GetMapping("/products/update/{id}")
	public String updateProduct(@PathVariable Long id, Model model) {
		myLogger.info("Product " + productRepository.findById(id).get());
		Product product = productRepository.findById(id).get();
		List<Category> categories = (List<Category>) categoryRepository.findAll();
		
		model.addAttribute("product", product);
		model.addAttribute("categories", categories);
		return "products-form";
	}
	
	//---------------------------------
	
	@GetMapping("/products/addCategory")
	public String addCategoryForm(Model model) {
		model.addAttribute("category", new Category());
		return "category-form";
	}
	
	@PostMapping("/products/processCategoryForm")
	public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "category-form";
		} else {
			categoryRepository.save(category);
			return "redirect:/products";
		}
	}
	
	
	@GetMapping("/products/{id}")
	public String showForCategory(@PathVariable Long id, Model model) {
		Category category = categoryRepository.findById(id).get();
		myLogger.info("Category: " + category);
		
		model.addAttribute("category", category);
//		List<Test> findByUsers_UserName(String userName)
		
		
		List<Product> products = productRepository.findByCategories_Id(id);
		List<Category> categories = (List<Category>) categoryRepository.findAll();
		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
		
//		List<Product> products = (List<Product>) productRepository.findAll();
		myLogger.info("Filtered products?: " + products);
		
		return "products";
	}
	
	@GetMapping("/products/updateCategory/{id}")
	public String updateCategory(@PathVariable Long id, Model model) {
		Category category = categoryRepository.findById(id).get();
		model.addAttribute("category", category);
		return "category-form";
	}
	
	@GetMapping("/products/deleteCategory/{id}")
	public String deleteCategory(@PathVariable Long id) {
		Category category = categoryRepository.findById(id).get();
		categoryRepository.delete(category);
		return "redirect:/products";
	}
}
