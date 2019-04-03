package com.geekbrains.springbootproject.controllers;

import com.geekbrains.springbootproject.entities.Product;
import com.geekbrains.springbootproject.services.ProductsService;
import com.geekbrains.springbootproject.utils.ProductNotFoundException;
import com.geekbrains.springbootproject.utils.ProductsErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductsRestController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productsService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getOneProduct(@PathVariable("id") Long id){
        return productsService.findById(id);
    }
//
//    @PostMapping("/products")
//    public Product addNewProduct(@RequestBody Product product){
//        return productsService.save(product);
//    }
//
//    @PutMapping("/products")
//    public Product updateProduct(@RequestBody Product product) {
//        return productsService.update(product);
//    }
//
//    @DeleteMapping("/products")
//    public int deleteProduct (@RequestBody Product product){
//        return productsService.delete(product);
//    }

    @ExceptionHandler
    public ResponseEntity<ProductsErrorResponse> handleException(ProductNotFoundException exc) {
        ProductsErrorResponse productsErrorResponse = new ProductsErrorResponse();
        productsErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        productsErrorResponse.setMessage(exc.getMessage());
        productsErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(productsErrorResponse, HttpStatus.NOT_FOUND);
    }
}
