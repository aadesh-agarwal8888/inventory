package com.example.inventory.model;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("shipments")
public class Shipment {

    @Id
    private String id;
    private ObjectId from;
    private ObjectId to;
    private List<InventoryItem> items;
    
    public Shipment() {
        // TODO Auto-generated constructor stub
    }

    public Shipment(
        String id,
        ObjectId from,
        ObjectId to,
        List<InventoryItem> items) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ObjectId getFrom() {
        return from;
    }

    public void setFrom(ObjectId from) {
        this.from = from;
    }

    public ObjectId getTo() {
        return to;
    }

    public void setTo(ObjectId to) {
        this.to = to;
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public void setItems(List<InventoryItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Shipment [id=" + id + ", from=" + from + ", to=" + to
            + ", items=" + items + "]";
    }
    
    
    
    

}
