package controller;

import model.Customer;
import model.Order;
import model.Product;
import model.ShoppingCart;

import java.util.*;
import java.io.*;

public class UserMenu {

    public static Scanner scan = new Scanner(System.in);
    static ShoppingCart cart = new ShoppingCart();
    static int option;

    public static void menu() {
        System.out.println("1. View All Product");
        System.out.println("2. Filter Product by Category");
        System.out.println("3. Filter Product by Price");
        System.out.println("4. Find item by name and Add to Cart");
        System.out.println("5. Order items in cart");
        System.out.println("6. View order by order ID");
    }

    public static void displayCatalog() {
        System.out.println("Display All Products");
        List<Catalog> fullList = Catalog.displayCatalogAll();
        System.out.println(fullList);
    }

    public static List<Product> displayCatalogCategory() {
        System.out.println("Filter by category");
        String filter = scan.nextLine();
        List<Product> filteredList = Catalog.displayCatalogFiltered(filter);
        System.out.println(filteredList);
        return filteredList;
    }

    public static List<Catalog> displayCatalogPrice(List<Catalog> list) {
        System.out.println("Sort by price");
        List<Catalog> filteredListPrice = Catalog.sortCatelogPrice(list);
        System.out.println(filteredListPrice);
        return filteredListPrice;
    }

    public static void findProductByName() {
        System.out.println("Find Product by name");
        String name = scan.nextLine();
        Product p = Catalog.searchProduct(name);
        if (p == null) {
            System.out.println("Product not found");
        } else {
            System.out.println(p);
        }
        System.out.println("Would you like to add this item to your cart?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        option = Integer.parseInt(scan.nextLine());
        if (option == 1) {
            cart.addLineItem(p);
            System.out.println(cart);
        }
    }

    public static void orderItems(Customer customer) throws IOException {
        System.out.println(" Place Order for items in cart");
        Order order = new Order(customer.getCustomerID(), cart.getTotalCost(),cart.getProducts());
        System.out.println(order);
        Order.addOrder(order);
        Order.saveOrders();
    }

    public static void displayCustomerOrderID() {
        System.out.println("Find order by Order ID");
        String id = scan.nextLine();
        Order matchedOrder = Order.displayOrderByID(id);
        System.out.println(matchedOrder);
    }
}
