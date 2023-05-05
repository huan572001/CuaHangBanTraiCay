package com.example.cuahangbantraicay.Modal;

public class Ecaluate {
    private String name;
    private String image;
    private String comment;
    private String time;
    private int stars;
    public Ecaluate(){

    }
    public Ecaluate(String name, String image, String comment,int stars) {
        this.name = name;
        this.image = image;
        this.comment = comment;
        this.stars=stars;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
