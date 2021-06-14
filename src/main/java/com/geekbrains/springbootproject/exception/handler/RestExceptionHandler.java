package com.geekbrains.springbootproject.exception.handler;

import com.geekbrains.springbootproject.exception.BadRequestException;
import com.geekbrains.springbootproject.exception.NotFoundException;
import com.geekbrains.springbootproject.dto.response.ProductsErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ProductsErrorResponse> handleAllException(BadRequestException exc) {
        ProductsErrorResponse productsErrorResponse = new ProductsErrorResponse();
        productsErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        productsErrorResponse.setMessage(exc.getMessage());
        productsErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(productsErrorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ProductsErrorResponse> handleException(NotFoundException exc) {
        ProductsErrorResponse productsErrorResponse = new ProductsErrorResponse();
        productsErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        productsErrorResponse.setMessage(exc.getMessage());
        productsErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(productsErrorResponse, HttpStatus.NOT_FOUND);
    }
}
