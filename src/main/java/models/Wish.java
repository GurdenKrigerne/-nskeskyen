package models;

public class Wish {

    private int wishId;
    private String titel;
    private double price;
    private String description;

    public Wish(int wishId, String titel, double price, String description) {
        this.wishId = wishId;
        this.titel = titel;
        this.price = price;
        this.description = description;

    }

    public Wish(){}

    public int getwishId() {
        return wishId;
    }

    public void setId(int id) {
        this.wishId = wishId;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;

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
