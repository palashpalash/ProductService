package com.example.ProductService.dao;


import com.example.ProductService.exception.ProductNotFoundException;
import com.example.ProductService.repository.ProductRepository;
import com.example.ProductService.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class ProductDao {

    @Autowired
    private RedisTemplate template;
    ProductRepository productRepository;
    String HASH_KEY= "products";



    @Autowired
    public ProductDao(ProductRepository productRepository) {

        this.productRepository=productRepository;
    }

    // Save product to Redis Hash
    public Product saveProduct(Product product) {

        template.opsForHash().put(HASH_KEY,product.getId(),product);
        return productRepository.save(product);
    }

    // Retrieve product by ID
    public Product getProductById(int productId) {
        Product product= (Product) template.opsForHash().get(HASH_KEY,productId);
        if(product==null)
        {
            Optional<Product> optionalProduct=productRepository.findById(productId);
            if(optionalProduct.isPresent())
            {
                Product actualProduct=optionalProduct.get();
                saveProduct(actualProduct);
                return actualProduct;
            }
            else
            {
                try {
                    throw new ProductNotFoundException("Product Not Found");
                } catch (ProductNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        return product;
    }



    // Retrieve all products


}
