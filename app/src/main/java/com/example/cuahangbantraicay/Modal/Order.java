package com.example.cuahangbantraicay.Modal;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private String address;
    private boolean status;
    private int user_id;

    public Order(){

    }

    public Order(int id, String address,int user_id){
        this.id = id;
        this.address = address;
        this.user_id = user_id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}

