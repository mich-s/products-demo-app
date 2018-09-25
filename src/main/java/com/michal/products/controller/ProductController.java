package com.michal.products.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.michal.products.service.ProductsService;

@Controller
public class ProductController {

//	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	private ProductsService productsService;
	
	public ProductController(ProductsService productsService) {
		this.productsService = productsService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	
	@GetMapping("/products")
	public String showHome(Model model) {
		model.addAttribute("products", productsService.findAllProducts());
		model.addAttribute("categories", productsService.findAllCategories());
		return "products";
	}
	
	@GetMapping("/products/addProduct")
	public String addProductForm(Model model) {
		model.addAttribute("product", new Product());
		List<Category> categories = (List<Category>) productsService.findAllCategories();
//		myLogger.info("--------------Categories: " + categories);
		model.addAttribute("categories", categories);
		return "products-form";
	}
	
	@PostMapping("/products/processProductForm")
	public String processProductForm(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			List<Category> categories = (List<Category>) productsService.findAllCategories();
			model.addAttribute("categories", categories);
			return "products-form";
		} else {
			productsService.saveProduct(product);
			return "redirect:/products";
		}
	}
	
	@GetMapping("/products/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productsService.deleteProductById(id);
		return "redirect:/products";
	}
	
	@GetMapping("/products/update/{id}")
	public String updateProduct(@PathVariable Long id, Model model) {
//		myLogger.info("Product " + productsService.findProductById(id));
		Product product = productsService.findProductById(id);
		List<Category> categories = (List<Category>) productsService.findAllCategories();
		
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
			productsService.saveCategory(category);
			return "redirect:/products";
		}
	}
	
	@GetMapping("/products/{id}")
	public String showForCategory(@PathVariable Long id, Model model) {
		Category category = productsService.findCategoryById(id);
//		myLogger.info("Category: " + category);
		
		model.addAttribute("category", category);
		
		List<Product> products = productsService.findProductsByCategories_Id(id);
		List<Category> categories = (List<Category>) productsService.findAllCategories();
		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
		
//		myLogger.info("Filtered products?: " + products);
		
		return "products";
	}
	
	@GetMapping("/products/updateCategory/{id}")
	public String updateCategory(@PathVariable Long id, Model model) {
		Category category = productsService.findCategoryById(id);
		model.addAttribute("category", category);
		return "category-form";
	}
	
	@GetMapping("/products/deleteCategory/{id}")
	public String deleteCategory(@PathVariable Long id) {
		Category category = productsService.findCategoryById(id);
		productsService.deleteCategory(category);
		return "redirect:/products";
	}
}
