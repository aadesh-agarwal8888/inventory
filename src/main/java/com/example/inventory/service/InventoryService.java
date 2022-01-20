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
    
    public List<Inventory> findAll() {
        List<Inventory> response = inventoryRepository.findAll();
        
        return response;
    }
    
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
    
    
    public Inventory saveOrUpdate(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
    
    public void deleteInventory(String id) {
        inventoryRepository.deleteById(id);
    }
    
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
    
    public void deleteItemFromInventory(String id, List<InventoryItem> items) {
        
        for(InventoryItem item: items)
            inventoryItemRepository.deleteInventoryItem(new ObjectId(id), item.getItemId());
        
    }

}
