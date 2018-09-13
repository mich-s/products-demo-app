package com.michal.products.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Category implements Comparable<Category>{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="this text field can''t be empty")
	/*@Size(min=1, message="at least 1 char is required")*/
	private String name;
	
	/*@ManyToMany(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="poduct_category", 
				joinColumns=@JoinColumn(name="category_id"), inverseJoinColumns=@JoinColumn(name="product_id"))
	private List<Product> products;*/
	@ManyToMany(mappedBy="categories")
	private List<Product> products;
	
	public Category() {
	}
	
	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
	}


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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", products=" + products + "]";
	}

	@Override
	public int compareTo(Category category) {
		int result = this.getName().toUpperCase().compareTo(category.getName().toUpperCase());
		return result;
	}
	
	
	
	
}
