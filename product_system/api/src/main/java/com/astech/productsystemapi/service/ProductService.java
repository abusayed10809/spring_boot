package com.astech.productsystemapi.service;

import com.astech.productsystemapi.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    List<Product> getAllProducts();

    boolean deleteProduct(Long id);

    Product getProductById(Long id);

    Product updateProduct(Long id, Product employee);
}