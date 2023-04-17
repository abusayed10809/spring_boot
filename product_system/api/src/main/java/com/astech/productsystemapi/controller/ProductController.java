package com.astech.productsystemapi.controller;

import com.astech.productsystemapi.entity.ProductEntity;
import com.astech.productsystemapi.model.Product;
import com.astech.productsystemapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add-product")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return  productService.getAllProducts();
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteProduct(@PathVariable Long id) {
        boolean deleted = false;
        deleted = productService.deleteProduct(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("product deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = null;
        product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                   @RequestBody Product product) {
        product = productService.updateProduct(id, product);
        return ResponseEntity.ok(product);
    }
}
