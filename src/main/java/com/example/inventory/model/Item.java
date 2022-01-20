package com.example.inventory.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("items")
public class Item {
    
    @Id
    private String id;
    private String name;
    private String desc;
    

    public Item() {
        // TODO Auto-generated constructor stub
    }


    public Item(String id, String name, String desc) {
        super();
        this.id = id;
        this.name = name;
        this.desc = desc;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getItemName() {
        return name;
    }


    public void setItemName(String name) {
        this.name = name;
    }


    public String getDesc() {
        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", desc=" + desc
            + "]";
    }
    
    

}
