package com.example.inventory.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.inventory.model.Item;
import com.example.inventory.repository.ItemRepository;

/**
 * Business Logic for managinf Items
 * @author aadesh
 *
 */
@Service
public class ItemService {

    public ItemService() {
        // TODO Auto-generated constructor stub
    }
    
    @Autowired
    ItemRepository itemRepository;
    
    /**
     * retrieves all the items
     * @return List of Items
     */
    public List<Item> findAll() {
        return itemRepository.findAll();
    }
    
    /**
     * Retrieves Item by ID
     * @param id - Item id to be searched
     * @return Item
     */
    public Item getByID(String id) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent())
            return item.get();
        
        return null;
    }
    
    /**
     * Retrieves Item by Name
     * @param name - Name of the item
     * @return item
     */
    public Item getByName(String name) {
        return itemRepository.findByName(name);
    }
    
    /**
     * Creates and Updates the Item
     * @param item - Item to be created/updated
     * @return Item
     */
    public Item saveOrUpdate(Item item) {
        return itemRepository.save(item);
    }
    
    /**
     * Deletes the Item
     * @param id Id of item to be deleted
     */
    public void deleteByID(String id) {
        itemRepository.deleteById(id);
    }
}
