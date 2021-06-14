package com.geekbrains.springbootproject.controller;

import com.geekbrains.springbootproject.entity.Product;
import com.geekbrains.springbootproject.service.CategoryService;
import com.geekbrains.springbootproject.service.impl.ProductsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductsServiceImpl productsServiceImpl;
    private final CategoryService categoryService;

    @GetMapping("/edit/{id}")
    public String addProductPage(@PathVariable("id") Long id, Model model) {

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("product", productsServiceImpl.findById(id));
        return "edit-product";
    }

    @PostMapping("/edit")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit-product";
        }
        if (productsServiceImpl.findByTitle(product)) {
            return "redirect:/";
        }
        model.addAttribute("product", product);
        model.addAttribute("productCreationError", "Product title already exists");
        return "edit-product";
    }
}
