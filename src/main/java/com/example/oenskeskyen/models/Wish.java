package com.example.oenskeskyen.models;

import java.util.ArrayList;
import java.util.List;

public class Wish {

    private int wishId;
    private String title;
    private double price;
    private String description;
    private int wishListId;
    private String url;


    public Wish(int wishId, String title, double price, String description, int wishListId, String url) {
        this.wishId = wishId;
        this.title = title;
        this.price = price;
        this.description = description;
        this.wishListId = wishListId;
        this.url = url;

    }

    public Wish() {
    }

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int id) {
        this.wishId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}