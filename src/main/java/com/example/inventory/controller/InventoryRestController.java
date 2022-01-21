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
import com.example.inventory.model.Inventory;
import com.example.inventory.model.InventoryItem;
import com.example.inventory.model.InventoryItemResponse;
import com.example.inventory.service.InventoryService;

/**
 * This class is responsible for managing inventory end points
 * @author aadesh agarwal
 */
@RestController
@RequestMapping("/inventories")
public class InventoryRestController {

    public InventoryRestController() {
    }
    
    @Autowired
    InventoryService inventoryService;
    
    /**
     * Retrieves All Inventories
     * @return ResponseEntitiy
     */
    @GetMapping
    public ResponseEntity<?> getAllInventories() {
        List<Inventory> response =  inventoryService.findAll();
        if(response == null || response.size() == 0)
            return new ResponseEntity<>("Inventory Not Found", HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    /**
     * Creates an Inventory
     * @param inventory - Inventory to be created
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<?> createInventory(@RequestBody Inventory inventory) {
        
        if(inventory.getInventoryName() == null || inventory.getLocation() == null || inventory.getInventoryName() == "" || inventory.getLocation() == "")
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        inventoryService.saveOrUpdate(inventory);
        return new ResponseEntity<>("Inventory Created", HttpStatus.OK);
    }
    
    /**
     * Updates an Inventory
     * @param inventory - Inventory to be updated
     * @return ResponseEntity
     */
    @PutMapping
    public ResponseEntity<?> updateInventory(@RequestBody Inventory inventory) {
        if(inventory.getId() == null || inventory.getInventoryName() == null || inventory.getLocation() == null)
            return new ResponseEntity<>("Inventory ID not Found", HttpStatus.BAD_REQUEST);
        inventoryService.saveOrUpdate(inventory);
        return new ResponseEntity<>("Inventory Updated", HttpStatus.OK);
    }
    
    /**
     * Deletes the Inventory
     * @param id - Id of inventory to be deleted
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable("id") String id) {
        if(id.equals(""))
            return new ResponseEntity<>("Inventory Id Not Found", HttpStatus.BAD_REQUEST);
        inventoryService.deleteInventory(id);
        return new ResponseEntity<>("Inventory Deleted", HttpStatus.OK);
    }
    
    /**
     * Retrieves all the Items of given Inventory
     * @param id - Inventory id
     * @return ResponseEntity
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getInventoryItems(@PathVariable String id) {
        
        if(id.equals(""))
            return new ResponseEntity<>("Inventory Id Not Found", HttpStatus.BAD_REQUEST);
        
        List<InventoryItemResponse> response = inventoryService.findItemsByID(id);
        
        if(response == null || response.size() == 0)
            return new ResponseEntity<>("No Items Found", HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    /**
     * Adds items to given Inventory
     * @param id - Inventory id
     * @return ResponseEntity
     */
    @PostMapping(value = "/{id}/add")
    public ResponseEntity<?> addItems(@PathVariable String id, @RequestBody List<InventoryItem> items) {
        
        if(id.equals(""))
            return new ResponseEntity<>("Inventory Id Not Found", HttpStatus.BAD_REQUEST);
        
        if(items.size() == 0)
            return new ResponseEntity<>("No Items to add", HttpStatus.BAD_REQUEST);
        
        inventoryService.addItemsToInventory(id, items);
        
        return new ResponseEntity<>("Items Added to Inventory", HttpStatus.OK);
    }
    
    /**
     * Removes items from given Inventory
     * @param id - Inventory id
     * @return ResponseEntity
     */
    @PostMapping(value = "/{id}/remove")
    public ResponseEntity<?> removeItems(@PathVariable String id, @RequestBody List<InventoryItem> items) {
        
        if(id.equals(""))
            return new ResponseEntity<>("Inventory Id Not Found", HttpStatus.BAD_REQUEST);
        
        if(items.size() == 0)
            return new ResponseEntity<>("No Items to Remove", HttpStatus.BAD_REQUEST);
        
        inventoryService.removeItemsFromInventory(id, items);
        
        return new ResponseEntity<>("Items Removed from Inventory", HttpStatus.OK);
    }

}
