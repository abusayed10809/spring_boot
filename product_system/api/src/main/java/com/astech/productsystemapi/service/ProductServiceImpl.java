package com.astech.productsystemapi.service;

import com.astech.productsystemapi.entity.ProductEntity;
import com.astech.productsystemapi.model.Product;
import com.astech.productsystemapi.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        ProductEntity productEntity = new ProductEntity();

        // copy product to product entity
        BeanUtils.copyProperties(product, productEntity);
        productRepository.save(productEntity);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<ProductEntity> productEntityList = productRepository.findAll();

        List<Product> productList = productEntityList
                .stream()
                .map(productEntity -> new Product(
                        productEntity.getId(),
                        productEntity.getName(),
                        productEntity.getPrice(),
                        productEntity.getQuantity(),
                        productEntity.getImageUrl()
                        ))
                .collect(Collectors.toList());
        return productList;
    }

    @Override
    public boolean deleteProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id).get();
        productRepository.delete(productEntity);
        return true;
    }

    @Override
    public Product getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id).get();
        Product product = new Product();
        BeanUtils.copyProperties(productEntity, product);
        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        ProductEntity productEntity = productRepository.findById(id).get();
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setQuantity(product.getQuantity());
        productEntity.setImageUrl(product.getImageUrl());

        productRepository.save(productEntity);
        return product;
    }
}
