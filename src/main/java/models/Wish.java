package models;

public class Wish {

    private int wishId;
    private String title;
    private double price;
    private String description;

    public Wish(int wishId, String title, double price, String description) {
        this.wishId = wishId;
        this.title = title;
        this.price = price;
        this.description = description;

    }

    public Wish(){}

    public int getWishId() {
        return wishId;
    }

    public void setId(int wishId) {
        this.wishId = wishId;
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
}
