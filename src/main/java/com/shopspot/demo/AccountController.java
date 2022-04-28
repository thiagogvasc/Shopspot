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
public class AccountController {
    
    private AccountRepository repository;
    
    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }
    
    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody Account account) {
        Optional<Account> accountFound = repository.findByUsername(account.getUsername());
        if (accountFound.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            repository.save(account);
            return ResponseEntity.ok("Account created successfully");
        }
    }
    
    @PostMapping("/login")
    ResponseEntity<Account> login(@RequestBody Account accountRequestBody) {
        Optional<Account> accountQueryResult = repository.findByUsername(accountRequestBody.getUsername());
        if (accountQueryResult.isPresent()) {
            Account account = accountQueryResult.get();
            if (account.getPassword().equals(accountRequestBody.getPassword())) {
                return ResponseEntity.ok(account);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PutMapping("/update")
    ResponseEntity<Account> update(@RequestBody Account accountRequestBody) {
        repository.save(accountRequestBody);
        return ResponseEntity.ok(accountRequestBody);
    }
    
    @GetMapping("/cart/{id}")
    ResponseEntity<List<Product>> getCart(@PathVariable String id) {
        var user = repository.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user.get().getProductsInCart());
        }
        return null;
    }
    
    @GetMapping("/account")
    ResponseEntity<List<Account>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }
}
