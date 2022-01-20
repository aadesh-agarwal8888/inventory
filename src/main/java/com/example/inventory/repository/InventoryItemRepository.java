package com.example.inventory.repository;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.example.inventory.model.InventoryItem;

public interface InventoryItemRepository extends MongoRepository<InventoryItem, String>{

    List<InventoryItem> findByInventoryId(ObjectId id);
    
    @Query(value = "{inventoryId: ?0, itemId: ?1}")
    InventoryItem findInventoryItem(ObjectId inventoryId, ObjectId itemId);
    
    @Query(value = "{inventoryId: ?0, itemId: ?1}")
    InventoryItem deleteInventoryItem(ObjectId inventoryId, ObjectId itemId);
    
}
