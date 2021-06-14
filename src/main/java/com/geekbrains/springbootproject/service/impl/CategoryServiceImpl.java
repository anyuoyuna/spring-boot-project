package com.geekbrains.springbootproject.service.impl;

import com.geekbrains.springbootproject.entity.Category;
import com.geekbrains.springbootproject.entity.repository.CategoryRepository;
import com.geekbrains.springbootproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return (List<Category>)categoryRepository.findAll();
    }
}
