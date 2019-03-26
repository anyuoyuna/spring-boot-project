package com.geekbrains.springbootproject.controllers;

import com.geekbrains.springbootproject.entities.Product;
import com.geekbrains.springbootproject.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MainController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/")
    public String showHomePage(Model model, @RequestParam(value = "min", required = false) Double min,
                               @RequestParam(value = "max", required = false) Double max) {
        Page<Product> page = productsService.getProductsByCost(PageRequest.of(0, 5), min, max);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("page", page);

        return "index";
    }

    @GetMapping("/info")
    public String showInfoPage(Model model) {
        return "info";
    }

    @PostMapping("/data")
    @ResponseBody
    public String procData(HttpServletRequest request) {
        System.out.println(request.getParameterMap().keySet());
        return "info";
    }

    @GetMapping("/product/edit/{id}")
    public String addProductPage(Model model, @PathVariable("id") Long id) {
        Product product = productsService.findById(id);
        if (product == null) {
            product = new Product();
        }
        model.addAttribute("product", product);
        return "edit-product";
    }

    // Binding Result после @ValidModel !!!
    @PostMapping("/product/edit")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit-product";
        }

        Product existing = productsService.findByTitle(product.getTitle());
        if (existing != null && (product.getId() == null || !product.getId().equals(existing.getId()))) {
            // product.setTitle(null);
            model.addAttribute("product", product);
            model.addAttribute("productCreationError", "Product title already exists");
            return "edit-product";
        }
        productsService.saveOrUpdate(product);
        return "redirect:/";
    }
}
