package com.example.oenskeskyen.models;

import java.util.List;

public class WishList {
    private int wishlistId;
    private String title;
    private String description;
    private int userId;

    public WishList(){}

    public WishList(int wishlistId, String title, String description, int userId) {
        this.wishlistId = wishlistId;
        this.title = title;
        this.description = description;
        this.userId = userId;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
