package com.geekbrains.springbootproject.service.impl;

import com.geekbrains.springbootproject.entity.Product;
import com.geekbrains.springbootproject.entity.repository.ProductsRepository;
import com.geekbrains.springbootproject.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository productsRepository;

    @Override
    public boolean findByTitle(Product product) {
        Product existing = productsRepository.findOneByTitle(product.getTitle());

        if (existing != null && product.getId().equals(existing.getId())) {
            productsRepository.save(product);
            return true;
        }
        return false;

    }

    @Override
    public Product findById(Long id) {
        return productsRepository.findById(id).orElse(new Product());
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productsRepository.findAll();
    }

    @Override
    public Page<Product> getProductsByCost(Pageable pageable, Double min, Double max) {
        if (min == null) {
            min = 0.0;
        }
        if (max == null) {
            max = Double.MAX_VALUE;
        }
        return productsRepository.findAllByPriceBetween(pageable, min, max);
    }

    @Override
    public Page<Product> getProductsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Product> productSpecification) {
        return productsRepository.findAll(productSpecification, PageRequest.of(pageNumber, pageSize));
    }
}
