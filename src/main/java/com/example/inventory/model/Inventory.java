package com.example.inventory.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Inventory Model that stores all the inventories
 * @author aadesh
 *
 */
@Document("inventories")
public class Inventory {

    
    @Id
    private String id;
    private String inventoryName;
    private String location;
    
    public Inventory() {
        // TODO Auto-generated constructor stub
    }

    public Inventory(
        String id,
        String inventoryName,
        String location) {
        super();
        this.id = id;
        this.inventoryName = inventoryName;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Inventory [id=" + id + ", inventoryName=" + inventoryName
            + ", location=" + location + "]";
    }    
    
    
    
}
