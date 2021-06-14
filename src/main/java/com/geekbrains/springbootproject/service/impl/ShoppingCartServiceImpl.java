package com.geekbrains.springbootproject.service.impl;

import com.geekbrains.springbootproject.entity.Product;
import com.geekbrains.springbootproject.component.ShoppingCart;
import com.geekbrains.springbootproject.service.ShoppingCartService;
import com.geekbrains.springbootproject.service.impl.ProductsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ProductsServiceImpl productsServiceImpl;

    @Override
    public ShoppingCart getCurrentCart(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @Override
    public void resetCart(HttpSession session) {
        session.removeAttribute("cart");
    }

    @Override
    public void addToCart(HttpSession session, Long productId) {
        Product product = productsServiceImpl.findById(productId);
        addToCart(session, product);
    }

    @Override
    public void addToCart(HttpSession session, Product product) {
        getCurrentCart(session).add(product);
    }

    @Override
    public void removeFromCart(HttpSession session, Long productId) {
        Product product = productsServiceImpl.findById(productId);
        removeFromCart(session, product);
    }

    @Override
    public void removeFromCart(HttpSession session, Product product) {
        getCurrentCart(session).remove(product);
    }

    @Override
    public void setProductCount(HttpSession session, Long productId, Long quantity) {
        ShoppingCart cart = getCurrentCart(session);
        Product product = productsServiceImpl.findById(productId);
        cart.setQuantity(product, quantity);
    }

    @Override
    public void setProductCount(HttpSession session, Product product, Long quantity) {
        getCurrentCart(session).setQuantity(product, quantity);
    }

    @Override
    public double getTotalCost(HttpSession session) {
        return getCurrentCart(session).getTotalCost();
    }
}
