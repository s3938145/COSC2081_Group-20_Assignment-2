package model;

import java.io.Serializable;
import java.util.*;

public class Product implements Serializable{

    private static final long serialVersionUID = 1L;
    protected String productID;
    private String name;
    private String cat;
    private int price;

    private int quantity = 1;

    public Product (String name, String cat, int price) {
        this.name = name;
        this.cat = cat;
        this.price = price;
    }


    public String getProductID() { return productID;}

    public String getName() {
        return name;
    }

    public String getCat() {
        return cat;
    }

    public int getPrice() {

        return price * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity = this.quantity + quantity;
    }

    public void updatePrice(int price) {

        this.price = price;
    }

    public static Comparator<Product> sortByPrice = (p1, p2) -> {
        int price1 = p1.getPrice();
        int price2 = p2.getPrice();

        return price1-price2;
    };

    @Override
    public String toString() {
        return "Product " + '\n' +
                " ID: " + productID + '\n' +
                " name: " + name + '\n' +
                " Category: " + cat + '\n' +
                " Price: " + price + " VND" + '\n' +
                " Quantity: " + quantity +
                "\n";

    }
}


