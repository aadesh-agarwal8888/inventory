package com.example.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.example.inventory.model.Inventory;
import com.example.inventory.model.InventoryItem;
import com.example.inventory.model.InventoryItemResponse;
import com.example.inventory.model.Item;
import com.example.inventory.repository.InventoryItemRepository;
import com.example.inventory.repository.InventoryRepository;

/**
 * Manages Services for inventory
 * @author aadesh
 *
 */
@Service
public class InventoryService {

    public InventoryService() {
        // TODO Auto-generated constructor stub
    }
    
    @Autowired
    InventoryRepository inventoryRepository;
    
    @Autowired
    InventoryItemRepository inventoryItemRepository;
    
    @Autowired
    MongoTemplate mongoTemplate;
    
    @Autowired
    ItemService itemService;
    
    /**
     * Retrieves all inventories
     * @return List of Inventory
     */
    public List<Inventory> findAll() {
        List<Inventory> response = inventoryRepository.findAll();
        
        return response;
    }
    
    
    /**
     * Retrieves all the items of inventory
     * @param id - inventory id
     * @return List of Inventory Item Response
     */
    public List<InventoryItemResponse> findItemsByID(String id) {
        
        Optional<Inventory> optional = inventoryRepository.findById(id);
        if(!optional.isPresent())
            return null;
        
        List<InventoryItem> items = inventoryItemRepository.findByInventoryId(new ObjectId(id));
        
        List<InventoryItemResponse> response = new ArrayList<>();
        
        for(InventoryItem item: items) {
            
            Item temp = itemService.getByID(item.getItemId().toString());
            InventoryItemResponse tempResponse = new InventoryItemResponse(temp, item.getNos());
            response.add(tempResponse);
            
        }
        
        return response;
        
    }
    
    
    /**
     * Creates or updates an inventory
     * @param inventory - Inventory to be created
     * @return Created Inventory
     */ 
    public Inventory saveOrUpdate(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
    
    /**
     * Deletes the inventory
     * @param id - ID of the inventory to be deleted
     */
    public void deleteInventory(String id) {
        inventoryRepository.deleteById(id);
    }
    
    
    /**
     * Adds the items to the inventory
     * @param id - Inventory id where the items has to be inserted
     * @param items - List of items to be inserted
     * @return List of inventory items
     */
    public List<InventoryItem> addItemsToInventory(String id, List<InventoryItem> items) {
        
        List<InventoryItem> result = new ArrayList<>();
        
        for(InventoryItem item: items) {
            
            Query query = new Query();
            query.addCriteria(Criteria.where("inventoryId").is(new ObjectId(id)).and("itemId").is(item.getItemId()));
            Update update = new Update();
            update.inc("nos", item.getNos());
            
            InventoryItem temp = mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true).upsert(true), InventoryItem.class);
            result.add(temp);
        }
        
        return result;
    }
    
    /**
     * Removes the items from the inventory
     * @param id - Id of the inventory whos items are to be removed
     * @param items - List of items to be removed
     * @return List of updated inventory items
     */
    public List<InventoryItem> removeItemsFromInventory(String id, List<InventoryItem> items) {
        
        List<InventoryItem> result = new ArrayList<>();
        
        for(InventoryItem item: items) {
            
            Query query = new Query();
            query.addCriteria(Criteria.where("inventoryId").is(new ObjectId(id)).and("itemId").is(item.getItemId()));
            Update update = new Update();
            update.inc("nos", item.getNos()*-1);
            
            InventoryItem temp = mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true).upsert(true), InventoryItem.class);
            if(temp.getNos() == 0)
                inventoryItemRepository.deleteInventoryItem(new ObjectId(id), temp.getItemId());
            result.add(temp);
        }
        
        return result;
    }
    
    /**
     * Deletes Items from the Inventory
     * @param id - Inventory ID
     * @param items - List of items to be deleted
     */
    public void deleteItemFromInventory(String id, List<InventoryItem> items) {
        
        for(InventoryItem item: items)
            inventoryItemRepository.deleteInventoryItem(new ObjectId(id), item.getItemId());
        
    }

}
