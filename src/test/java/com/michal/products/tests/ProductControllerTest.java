package com.michal.products.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.michal.products.controller.ProductController;
import com.michal.products.entity.Category;
import com.michal.products.entity.Product;
import com.michal.products.service.ProductsService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	private static List<Category> categoriesStub;
	private static List<Product> productsStub;
	private static final String CONTENT_TYPE = "text/html;charset=UTF-8";
	
	@Autowired
	private MockMvc mockMvc;
	
    @MockBean
    private ProductsService productsService;
	
    @BeforeClass
    public static void setup() {
    	categoriesStub = Arrays.asList(new Category(1L, "grocery"));
		
		productsStub = Arrays.asList(new Product(1L,"tomato",0.99, categoriesStub),
									 new Product(2L, "juice", 3.52, categoriesStub),
									 new Product(3L, "bread", 2.05, categoriesStub));
    }
    
	@Test
	public void testSuccessfulRenderProductsView() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/products")
													   .accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(view().name("products"))
				.andExpect(content().contentType(CONTENT_TYPE));
		
	}
	
	@Test
	public void testSuccessfulFindAllProductsAndCheckProductWithId2() throws Exception {
		
		when(productsService.findAllProducts()).thenReturn(productsStub);
		
		mockMvc.perform(get("/products"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("products"))
			   .andExpect(model().attribute("products", hasSize(3)))
			   .andExpect(model().attribute("products", hasItem(
					   allOf(hasProperty("id", is(2L)),
							 hasProperty("name", is("juice")),
							 hasProperty("price", equalTo(3.52)),
							 hasProperty("categories", is(categoriesStub))
						)
				)));
		
		verify(productsService, times(1)).findAllProducts();
	}
	
	@Test
	public void testSuccessfulFindProductsByCategoriesId() throws Exception {
		when(productsService.findProductsByCategories_Id(1L)).thenReturn(productsStub);
		
		mockMvc.perform(get("/products/{id}", 1L))
			   .andExpect(status().isOk())
			   .andExpect(view().name("products"))
			   .andExpect(model().attribute("products", hasSize(3)));
	}
	
	@Test
	public void testCreateProductAndRedirectUrl() throws Exception {
		
		mockMvc.perform(post("/products/processProductForm")
					.param("name", "butter")
					.param("price", Double.toString(1.59))
				)
			   .andExpect(status().is3xxRedirection())
			   .andExpect(redirectedUrl("/products"));
		
	}
	

	
	
}
