package com.example.cuahangbantraicay.Modal;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String image;
    private float price_in;
    private float price_sell;
    private String content;
    private int category_id;
    private int discout;
    private int quantity;
    private int quantity_sold;
    private Boolean status;

    private  int total_quantity;
    public Product(){

    }
    public Product(String name , int total_quantity){
        this.name = name;

        this.total_quantity = total_quantity;
    }

    public Product(int id, String name, String image, float price_sell, int discout, int quantity_sold, Boolean status, int total_quantity) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price_sell = price_sell;
        this.discout = discout;
        this.quantity_sold = quantity_sold;
        this.status = status;
        this.total_quantity = total_quantity;
    }
    public Product(int id, String name, String image, Float price_in, Float price_sell, String content, int category_id, int discout, int quantity, int quantity_sold, Boolean status, int total_quantity) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price_in = price_in;
        this.price_sell = price_sell;
        this.content = content;
        this.category_id = category_id;
        this.discout = discout;
        this.quantity = quantity;
        this.quantity_sold = quantity_sold;
        this.status = status;
        this.total_quantity = total_quantity;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }
    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice_in() {
        return price_in;
    }

    public void setPrice_in(Float price_in) {
        this.price_in = price_in;
    }

    public float getPrice_sell() {
        return price_sell;
    }

    public void setPrice_sell(float price_sell) {
        this.price_sell = price_sell;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getDiscout() {
        return discout;
    }

    public void setDiscout(int discout) {
        this.discout = discout;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity_sold() {
        return quantity_sold;
    }

    public void setQuantity_sold(int quantity_sold) {
        this.quantity_sold = quantity_sold;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
