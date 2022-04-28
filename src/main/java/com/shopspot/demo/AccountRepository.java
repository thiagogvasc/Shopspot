/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopspot.demo;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author tgoncalvesva2020
 */
public interface AccountRepository extends MongoRepository<Account, String> {
    Optional<Account> findByUsername(String username);
}
