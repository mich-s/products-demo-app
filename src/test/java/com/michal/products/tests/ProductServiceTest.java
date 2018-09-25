package com.michal.products.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.michal.products.entity.Category;
import com.michal.products.entity.Product;
import com.michal.products.repository.ProductRepository;
import com.michal.products.service.ProductsServiceImpl;

public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductsServiceImpl productsService;
	
	private static List<Product> productsStub;
	private static List<Category> categoriesStub;
	
	@BeforeClass
	public static void setup() {
		categoriesStub = Arrays.asList(new Category(1L, "grocery"));
		
		productsStub = Arrays.asList(new Product(1L,"tomato",0.99, categoriesStub),
									 new Product(2L, "juice", 3.52, categoriesStub),
									 new Product(3L, "bread", 2.05, categoriesStub));
	}
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	/*@Test
	public void shouldReturnAllProducts() {
		ProductRepository productRepository = mock(ProductRepository.class);
		CategoryRepository categoryRepository = mock(CategoryRepository.class);
		when(productRepository.findAll()).thenReturn(Arrays.asList(new Product(), new Product()));
		
		ProductsService productsService = new ProductsServiceImpl(productRepository, categoryRepository);
		
		List<Product> products = productsService.findAllProducts();
		
		assertNotNull(products);
	}*/
	
	@Test
	public void shouldReturnAllProducts() {
		when(productRepository.findAll()).thenReturn(productsStub);
		
		List<Product> products = productsService.findAllProducts();
		
		assertEquals(products.size(), 3);
	}
	
	@Test
	public void shouldReturnAllProductsByGivenCategoryId() {
		when(productRepository.findByCategories_Id(categoriesStub.get(0).getId())).thenReturn(productsStub);
		
		List<Product> products = productsService.findProductsByCategories_Id(categoriesStub.get(0).getId());
		
		assertThat(products, equalTo(productsStub));
	}
	
	
}
