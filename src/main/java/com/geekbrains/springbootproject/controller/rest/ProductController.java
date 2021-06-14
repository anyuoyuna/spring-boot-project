package com.geekbrains.springbootproject.controller.rest;

import com.geekbrains.springbootproject.entity.Product;
import com.geekbrains.springbootproject.service.impl.ProductsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductsServiceImpl productsServiceImpl;

    @GetMapping
    public List<Product> getAllProducts(){
        return productsServiceImpl.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productsServiceImpl.findById(id);
    }
}
