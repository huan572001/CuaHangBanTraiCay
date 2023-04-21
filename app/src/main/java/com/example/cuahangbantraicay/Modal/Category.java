package com.example.cuahangbantraicay.Modal;

import java.io.Serializable;

public class Category  implements Serializable {
    private int Id;
    private String name;

    public Category(int id, String name) {
        Id = id;
        this.name = name;
    }
    public Category() {

    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
