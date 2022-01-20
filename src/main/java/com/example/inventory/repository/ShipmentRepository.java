package com.example.inventory.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.inventory.model.Shipment;

public interface ShipmentRepository extends MongoRepository<Shipment, String> {

}
