package com.geekbrains.springbootproject.repositories;

import com.geekbrains.springbootproject.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends PagingAndSortingRepository<Product, Long> {
    //Product findWithIdOne();
}
