package com.geekbrains.springbootproject.repositories;

import com.geekbrains.springbootproject.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends CrudRepository<Product, Long> {
    Page<Product> findAllByCostBetween(Pageable pageable, double minCost, double maxCost);
    Product findOneByTitle(String title);
}
