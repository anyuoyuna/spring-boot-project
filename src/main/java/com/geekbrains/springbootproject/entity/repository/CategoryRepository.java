package com.geekbrains.springbootproject.entity.repository;

import com.geekbrains.springbootproject.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
