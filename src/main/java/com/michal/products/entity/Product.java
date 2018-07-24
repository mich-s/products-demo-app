package com.michal.products.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

@Entity
public class Product implements Comparable<Product>{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;	
	private double price;
	
	/*@ManyToMany(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="product_category",
				joinColumns=@JoinColumn(name="product_id"),
				inverseJoinColumns=@JoinColumn(name="category_id"))
	private List<Category> categories;*/
	
	@ManyToMany(cascade= CascadeType.MERGE)
	@JoinTable(name="product_category",
				joinColumns=@JoinColumn(name="product_id"),
				inverseJoinColumns=@JoinColumn(name="category_id"))
	private List<Category> categories = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

	@Override
	public int compareTo(Product product) {
		int result = this.getName().toUpperCase().compareTo(product.getName().toUpperCase());
		return result;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
	
}
