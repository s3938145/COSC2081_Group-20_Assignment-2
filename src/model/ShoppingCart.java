package model;


import java.io.*;
import java.util.*;

public class ShoppingCart {

    private List <Product> products = new ArrayList<>();

    public void addLineItem (Product product) {
        boolean exists = false;
        for (Product item: products) {
            if (item.getName() == product.getName()) {
                item.addQuantity(product.getQuantity());
                exists = true;
            }
        }
        if (!exists) {
            products.add(product);
        }
    }

    public int getTotalCost() {
        return products.stream()
                .mapToInt(Product::getPrice)
                .sum();
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "products=" + products +
                '}';
    }
}
