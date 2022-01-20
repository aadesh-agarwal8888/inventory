package com.example.inventory.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.inventory.model.Item;
import com.example.inventory.repository.ItemRepository;

@Service
public class ItemService {

    public ItemService() {
        // TODO Auto-generated constructor stub
    }
    
    @Autowired
    ItemRepository itemRepository;
    
    public List<Item> findAll() {
        return itemRepository.findAll();
    }
    
    public Item getByID(String id) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent())
            return item.get();
        
        return null;
    }
    
    public Item getByName(String name) {
        return itemRepository.findByName(name);
    }
    
    public Item saveOrUpdate(Item item) {
        return itemRepository.save(item);
    }
    
    public void deleteByID(String id) {
        itemRepository.deleteById(id);
    }
}
