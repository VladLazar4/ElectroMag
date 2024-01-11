package com.example.demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private String id;

    private String category;
    private String name;
    private float price;
    private String description;
    private int stock;
    private String postedBy;

    public Product() {
    }

    public Product(int id, String category, String name, float price, String description, int stock, String postedBy) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.postedBy = postedBy;
    }

    public String getProduct() {
        return "'" + getId() + "', '" + getCategory() + "', '" + getName() + "', '" + getPrice() + "', '" + getDescription() + "', '" + getStock() + "', '" + getPostedBy() + "'";
    }
}


//package com.example.demo;
//
//public class Product {
//    int id;
//    String category;
//    String name;
//    float price;
//    String description;
//    int stock;
//    String postedBy;
//    Product(int id, String category, String name, float price, String description, int stock, String postedBy){
//        this.id = id;
//        this.category = category;
//        this.name = name;
//        this.price = price;
//        this.description = description;
//        this.stock = stock;
//        this.postedBy = postedBy;
//    }
//    Product(int id){
//        this.id = id;
//    }
//
//    public String getProduct(){
//        String str="";
//        str+="'"+getId()+"', '"+getCategory()+"', '"+getName()+"', '"+getPrice()+"', '"+getDescription()+"', '"+getStock()+"', '"+getPostedBy()+"'";
//        return str;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public float getPrice() {
//        return price;
//    }
//
//    public void setPrice(float price) {
//        this.price = price;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public int getStock() {
//        return stock;
//    }
//
//    public void setStock(int stock) {
//        this.stock = stock;
//    }
//
//    public String getPostedBy() {
//        return postedBy;
//    }
//
//    public void setPostedBy(String postedBy) {
//        this.postedBy = postedBy;
//    }
//}
