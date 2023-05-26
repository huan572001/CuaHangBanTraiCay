package com.example.cuahangbantraicay.Modal;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class WaitingForReview implements Parcelable {
    private int product_id;
    private int order_id;
    private String img;
    private String name;
    private float price;
    private int quantity;
    public WaitingForReview(){

    }
    public WaitingForReview(int product_id,String img, String name, float price, int quantity) {
        this.product_id=product_id;
        this.img = img;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    protected WaitingForReview(Parcel in) {
        order_id=in.readInt();
        product_id = in.readInt();
        img = in.readString();
        name = in.readString();
        price = in.readFloat();
        quantity = in.readInt();
    }

    public static final Creator<WaitingForReview> CREATOR = new Creator<WaitingForReview>() {
        @Override
        public WaitingForReview createFromParcel(Parcel in) {
            return new WaitingForReview(in);
        }

        @Override
        public WaitingForReview[] newArray(int size) {
            return new WaitingForReview[size];
        }
    };

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(order_id);
        parcel.writeInt(product_id);
        parcel.writeString(img);
        parcel.writeString(name);
        parcel.writeFloat(price);
        parcel.writeInt(quantity);
    }
}
