package com.example.cuahangbantraicay.Modal;

import java.io.Serializable;

public class Product implements Serializable {
    private int resourceId;
    private String name;

    public Product(int resourceId, String name) {
        this.resourceId = resourceId;
        this.name = name;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
