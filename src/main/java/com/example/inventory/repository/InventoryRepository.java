package com.example.inventory.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.example.inventory.model.Inventory;
import com.example.inventory.model.InventoryItem;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
    
    @Query(value = "{id: ?0, 'items.id': ?1}", fields = "{ 'items.id': 1}")
    InventoryItem findInventoryItemByIDAndItemsID(String inventoryId, String itemId);
    
    @Query(value = "{id: ?0}")
    Inventory findInventory(String inventoryId);
    
}