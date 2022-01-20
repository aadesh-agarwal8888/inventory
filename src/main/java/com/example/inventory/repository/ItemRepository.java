package com.example.inventory.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.inventory.model.Item;

public interface ItemRepository extends MongoRepository<Item, String> {

    Item findByName(String name);

}
