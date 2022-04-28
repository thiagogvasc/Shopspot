/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopspot.demo;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class SellerController {
    
    private SellerRepository repository;
    
    public SellerController(SellerRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping("/seller")
    ResponseEntity<List<Seller>> getAllSellers() {
        return ResponseEntity.ok(repository.findAll());
    }
    
    @PutMapping("/seller")
    ResponseEntity<Seller> update(@RequestBody Seller seller) {
        repository.save(seller);
        return ResponseEntity.ok(seller);
    }
    
    @PostMapping("/seller/register")
    ResponseEntity<String> register(@RequestBody Seller seller) {
        Optional<Seller> sellerFound = repository.findByUsername(seller.getUsername());
        if (sellerFound.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            repository.save(seller);
            return ResponseEntity.ok("seller created successfully");
        }
    }
    
    @PostMapping("/seller/login")
    ResponseEntity<Seller> login(@RequestBody Seller sellerRequestBody) {
        Optional<Seller> sellerQueryResult = repository.findByUsername(sellerRequestBody.getUsername());
        if (sellerQueryResult.isPresent()) {
            Seller seller = sellerQueryResult.get();
            if (seller.getPassword().equals(sellerRequestBody.getPassword())) {
                return ResponseEntity.ok(seller);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
