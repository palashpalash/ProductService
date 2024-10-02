package com.example.ProductService;

import com.example.ProductService.dao.ProductDao;
import com.example.ProductService.entity.Product;
import com.example.ProductService.exception.ProductNotFoundException;
import com.example.ProductService.repository.ProductRepository;
import com.example.ProductService.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceApplicationTests {


	@Mock
	private ProductDao productDao;

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddProduct() {
		// Arrange
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");
		product.setPrice(100.0);

		when(productDao.saveProduct(product)).thenReturn(product);

		// Act
		Product createdProduct = productService.addProduct(product);

		// Assert
		assertEquals(product, createdProduct);
		verify(productDao, times(1)).saveProduct(product);
	}

	@Test
	void testGetProduct() {
		// Arrange
		int productId = 1;
		Product product = new Product();
		product.setId(productId);
		product.setName("Test Product");
		product.setPrice(100.0);

		when(productDao.getProductById(productId)).thenReturn(product);

		// Act
		Product fetchedProduct = productService.getProduct(productId);

		// Assert
		assertEquals(product, fetchedProduct);
		verify(productDao, times(1)).getProductById(productId);
	}

	@Test
	void testGetAllProducts() {
		// Arrange
		Product product1 = new Product();
		product1.setId(1);
		product1.setName("Test Product 1");
		product1.setPrice(100.0);

		Product product2 = new Product();
		product2.setId(2);
		product2.setName("Test Product 2");
		product2.setPrice(150.0);

		List<Product> products = Arrays.asList(product1, product2);
		when(productRepository.findAll()).thenReturn(products);

		// Act
		List<Product> allProducts = productService.getAllProducts();

		// Assert
		assertEquals(2, allProducts.size());
		assertEquals(products, allProducts);
		verify(productRepository, times(1)).findAll();
	}

	/*@Test
	void testGetProductThrowsExceptionWhenNotFound() {
		// Arrange
		int productId = 101;

		// Mocking the behavior of productDao to return null for the non-existent product
		when(productDao.getProductById(productId)).thenThrow(ProductNotFoundException.class);

		ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
			productService.getProduct(productId);
		});

		// Assert
		assertEquals("Product Not Found", exception.getMessage());
	}*/

}
