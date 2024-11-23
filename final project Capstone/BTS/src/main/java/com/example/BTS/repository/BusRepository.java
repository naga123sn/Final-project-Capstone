/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BTS.repository;

/**
 *
 * @author hp
 */


import com.example.BTS.model.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BusRepository extends MongoRepository<Bus, String> {

    // Custom query to find buses by fromLocation and toLocation
    List<Bus> findByFromLocationAndToLocation(String fromLocation, String toLocation);

    @Override
    Optional<Bus> findById(String id); // Accepting String id

    @Override
    void deleteById(String id); // Accepting String id
}



