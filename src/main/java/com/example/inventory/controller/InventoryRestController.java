package com.example.inventory.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.inventory.model.Inventory;
import com.example.inventory.model.InventoryItem;
import com.example.inventory.model.InventoryItemResponse;
import com.example.inventory.service.InventoryService;

@RestController
@RequestMapping("/inventories")
public class InventoryRestController {

    public InventoryRestController() {
        // TODO Auto-generated constructor stub
    }
    
    @Autowired
    InventoryService inventoryService;
    
    @GetMapping
    public ResponseEntity<?> getAllInventories() {
        List<Inventory> response =  inventoryService.findAll();
        if(response == null || response.size() == 0)
            return new ResponseEntity<>("Inventory Not Found", HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> createInventory(@RequestBody Inventory inventory) {
        inventoryService.saveOrUpdate(inventory);
        return new ResponseEntity<>("Inventory Created", HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<?> updateInventory(@RequestBody Inventory inventory) {
        inventoryService.saveOrUpdate(inventory);
        return new ResponseEntity<>("Inventory Updated", HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable("id") String id) {
        inventoryService.deleteInventory(id);
        return new ResponseEntity<>("Inventory Deleted", HttpStatus.OK);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getInventoryItems(@PathVariable String id) {
        List<InventoryItemResponse> response = inventoryService.findItemsByID(id);
        
        if(response == null || response.size() == 0)
            return new ResponseEntity<>("No Items Found", HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping(value = "/{id}/add")
    public ResponseEntity<?> addItems(@PathVariable String id, @RequestBody List<InventoryItem> items) {
        
        if(items.size() == 0)
            return new ResponseEntity<>("No Items to add", HttpStatus.BAD_REQUEST);
        
        inventoryService.addItemsToInventory(id, items);
        
        return new ResponseEntity<>("Items Added to Inventory", HttpStatus.OK);
    }
    
    @PostMapping(value = "/{id}/remove")
    public ResponseEntity<?> removeItems(@PathVariable String id, @RequestBody List<InventoryItem> items) {
        
        if(items.size() == 0)
            return new ResponseEntity<>("No Items to Remove", HttpStatus.BAD_REQUEST);
        
        inventoryService.removeItemsFromInventory(id, items);
        
        return new ResponseEntity<>("Items Removed from Inventory", HttpStatus.OK);
    }

}
