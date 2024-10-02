package com.example.ProductService.service;

import com.example.ProductService.dao.ProductDao;
import com.example.ProductService.repository.ProductRepository;
import com.example.ProductService.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;

    ProductDao productDao;
    @Autowired
    public ProductService(ProductDao productDao,ProductRepository productRepository)
    {
        this.productDao=productDao;
        this.productRepository=productRepository;

    }

    public Product addProduct(Product product)
    {
        return productDao.saveProduct(product);
    }

    public Product getProduct(int id) {
        return  productDao.getProductById(id);

    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
