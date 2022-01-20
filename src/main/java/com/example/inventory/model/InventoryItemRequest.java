package com.example.inventory.model;

import org.springframework.data.annotation.Id;

public class InventoryItemRequest {

    @Id
    private String id;
    private int nos;
    
    public InventoryItemRequest() {
        // TODO Auto-generated constructor stub
    }

    public InventoryItemRequest(String id, int nos) {
        super();
        this.id = id;
        this.nos = nos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNos() {
        return nos;
    }

    public void setNos(int nos) {
        this.nos = nos;
    }

    @Override
    public String toString() {
        return "InventoryItemRequest [id=" + id + ", nos=" + nos + "]";
    }
    
    

}
