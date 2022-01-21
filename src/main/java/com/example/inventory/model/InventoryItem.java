package com.example.inventory.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model of Inventory Object that stores all the items in inventory
 * @author aadesh
 *
 */
@Document("inventory")
public class InventoryItem {
    
    @Id
    private String id;
    
    private ObjectId inventoryId;
    
    private ObjectId itemId;
    
    private int nos;

    public InventoryItem() {
        // TODO Auto-generated constructor stub
    }

    public InventoryItem(
        String id,
        ObjectId inventoryId,
        ObjectId itemId,
        int nos) {
        super();
        this.id = id;
        this.inventoryId = inventoryId;
        this.itemId = itemId;
        this.nos = nos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ObjectId getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(ObjectId inventoryId) {
        this.inventoryId = inventoryId;
    }

    public ObjectId getItemId() {
        return itemId;
    }

    public void setItemId(ObjectId itemId) {
        this.itemId = itemId;
    }

    public int getNos() {
        return nos;
    }

    public void setNos(int nos) {
        this.nos = nos;
    }

    @Override
    public String toString() {
        return "InventoryItem [id=" + id + ", inventoryId=" + inventoryId
            + ", itemId=" + itemId + ", nos=" + nos + "]";
    }

    
    
    

}
