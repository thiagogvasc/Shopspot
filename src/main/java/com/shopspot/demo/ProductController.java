/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopspot.demo;

import java.util.List;
import java.util.Optional;
import static org.springframework.data.mongodb.core.aggregation.MergeOperation.UniqueMergeId.id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tgoncalvesva2020
 */
@RestController
public class ProductController {

    private ProductRepository repository;
    private SellerRepository sellerRepository;

    public ProductController(ProductRepository reposiroty, SellerRepository sellerRepository) {
        this.repository = reposiroty;
        this.sellerRepository = sellerRepository;
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Optional<Product> productQueryResult = repository.findById(id);
        if (productQueryResult.isPresent()) {
            Product product = productQueryResult.get();
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.ok(productQueryResult.get());
        }
    }
    
    @PutMapping("product/{id}")
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody Product product) {
        repository.save(product);
        return ResponseEntity.ok("Product created succssfully");
    }

    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        repository.save(product);
        return ResponseEntity.ok("Product created succssfully");
    }
    
    @GetMapping("/products/seller/{sellerId}")
    public ResponseEntity<List<Product>> getInventoryBySellerId(@PathVariable String sellerId) {
        return ResponseEntity.ok(repository.findBySellerId(sellerId));
    }
    
    @DeleteMapping("/products/{id}") 
    public ResponseEntity<String> removeProduct(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.ok("product removed successfylly");
    }

}
