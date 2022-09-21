package controller;

import model.Order;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AdminMenu extends Menu{

    public static Scanner scan = new Scanner(System.in);

    public static void menu() {
        System.out.println("1. Add Product");
        System.out.println("2. View All Products");
        System.out.println("3. Filter catalog based on category");
        System.out.println("4. Display all orders");
        System.out.println("5. Display a customer's orders");
        System.out.println("6. Display all orders made on a particular date");
        System.out.println("7. Display order based on ID");
        System.out.println("8. Display store total revenue");
    }

    public static void addNewProduct() throws IOException {
        System.out.println("Adding new product");
        //Add product name
        System.out.println("Product name");
        String name = scan.nextLine();
        //Add category
        System.out.println("Category");
        String cat = scan.nextLine();
        //Add price
        System.out.println("Price");
        int price = Integer.parseInt(scan.nextLine());
        Catalog.addProduct(new Catalog(name,cat,price));
    }

    public static void displayAllOrders(){
        System.out.println("Display all orders:");
        System.out.println(Order.displayAllOrders());
    }

    public static void displayCustomerOrder() {
        System.out.println("Search Order based on Customer");
        String customerName = scan.nextLine();
        List<Order> CustomerList = Order.displayOrdersByCustomer(customerName);
        System.out.println(CustomerList);
    }

    public static void displayDateOrder() {
        System.out.println("Search Order based on Date");
        System.out.println("Day");
        //Input day
        int day = Integer.parseInt(scan.nextLine());
        System.out.println("Month");
        //Input month
        int month = Integer.parseInt(scan.nextLine());
        System.out.println("Year");
        //Input year
        int year = Integer.parseInt(scan.nextLine());
        LocalDate ld = LocalDate.of(year, month, day);
        List<Order> DateOrder = Order.displayOrdersByDate(ld);
        if(DateOrder == null) {
            System.out.println("No orders on that date found");
        } else {
            System.out.println(DateOrder);
            System.out.println("");
        }
    }

    public static void displayOrderID() throws IOException {
        System.out.println("Find order by Order ID");
        String id = scan.nextLine();
        Order matchedOrder = Order.displayOrderByID(id);
        if (!(matchedOrder == null)){
            System.out.println(matchedOrder);
            System.out.println("Change Order status?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int option = Integer.parseInt(scan.nextLine());
            if (option == 1) {
                Order.changeStatus(matchedOrder);
                Order.saveOrders();
            }
        }
    }

    public static void storeRevenue() {
        int revenue = Order.totalRevenue();
        System.out.println("Store total revenue: " + revenue + " VND");
    }
}
