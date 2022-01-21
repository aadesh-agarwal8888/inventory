package com.example.inventory.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.inventory.model.Item;
import com.example.inventory.service.ItemService;

/**
 * Responsible for Item end points
 * @author aadesh agarwal
 *
 */
@RestController
@RequestMapping("/items")
public class ItemRestController {

    public ItemRestController() {
    }
    
    @Autowired
    ItemService itemService;
    
    /**
     * Retrieves all items
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<?> getAllItems() {
        List<Item> response = itemService.findAll();
        
        if(response == null || response.size() == 0)
            return new ResponseEntity<>("No Items Found", HttpStatus.NOT_FOUND);
        
            
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
    
    /**
     * Adds the item
     * @param item - Item to be added
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody Item item) {
        
        itemService.saveOrUpdate(item);
        
        return new ResponseEntity<>("Item added", HttpStatus.OK);
        
    }
    
    /**
     * Updates the given Item
     * @param item - item to be updated
     * @return ResponseEntity
     */
    @PutMapping
    public ResponseEntity<?> updateItem(@RequestBody Item item) {
        itemService.saveOrUpdate(item);
        return new ResponseEntity<>("Item updated", HttpStatus.OK);
    }
    
    /**
     * Deletes the Item
     * @param id - Item to be deleted
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") String id) {
        itemService.deleteByID(id);
        return new ResponseEntity<>("Item removed", HttpStatus.OK);
    }

}
