/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopspot.demo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author tgoncalvesva2020
 */
public interface ProductRepository extends MongoRepository<Product, String> {
    public List<Product> findBySellerId(String sellerId);
}
