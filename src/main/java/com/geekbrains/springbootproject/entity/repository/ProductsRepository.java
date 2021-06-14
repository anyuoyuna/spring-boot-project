package com.geekbrains.springbootproject.entity.repository;

import com.geekbrains.springbootproject.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Page<Product> findAllByPriceBetween(Pageable pageable, double minPrice, double maxPrice);
    Product findOneByTitle(String title);
}
