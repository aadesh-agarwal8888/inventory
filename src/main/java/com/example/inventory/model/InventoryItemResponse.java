package com.example.inventory.model;

public class InventoryItemResponse {

    
    private Item item;
    private int nos;
    
    public InventoryItemResponse() {
        // TODO Auto-generated constructor stub
    }

    public InventoryItemResponse(Item item, int nos) {
        super();
        this.item = item;
        this.nos = nos;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getNos() {
        return nos;
    }

    public void setNos(int nos) {
        this.nos = nos;
    }
    
    

}
