package com.example.ProductService.controller;

import com.example.ProductService.service.ProductService;
import com.example.ProductService.entity.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    public ProductController()
    {

    }
    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService=productService;
    }

    @GetMapping
    public List<Product> getAllProducts()
    {

        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id)
    {

        return productService.getProduct(id);
    }
    @PostMapping
    public Product addProduct(@Valid @RequestBody Product product)
    {

        return productService.addProduct(product);
    }

}
