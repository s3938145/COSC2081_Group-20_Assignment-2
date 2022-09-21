package model;


import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Order implements Serializable{

    private static final long serialVersionUID = 1L;

    private String orderID;

    private String customerID;
    private int totalInvoice;
    private LocalDate dateInvoice;
    private String status;

    private List<Product> cart;


    static List <Order> orderList = new ArrayList<Order>();
    private int id = orderList.size() + 1;
    public Order(String customerID, int totalInvoice, List<Product> cart) {
        this.orderID = "O-" + id;
        this.customerID = customerID;
        this.totalInvoice = totalInvoice;
        this.dateInvoice = LocalDate.now();
        this.status = "ORDERED";
        this.cart = cart;
    }

    public int getInvoice() {
        return this.totalInvoice;
    }
    public static void addOrder(Order order) {
        orderList.add(order);
    }

    public static void saveOrders() throws IOException {
        FileOutputStream FileOut = new FileOutputStream("orders.txt");
        ObjectOutputStream ObjectOut = new ObjectOutputStream(FileOut);
        ObjectOut.writeObject(orderList);
        ObjectOut.close();
    }

    public static void loadOrders() throws IOException, ClassNotFoundException {
        FileInputStream FileIn = new FileInputStream("orders.txt");
        ObjectInputStream ObjectIn = new ObjectInputStream(FileIn);
        orderList = (List<Order>) ObjectIn.readObject();
        ObjectIn.close();
    }

    public static void changeStatus (Order order) {
        order.status = "PAID";
        System.out.println("Order " + order.orderID + "\t" + order.status);
    }


    public static List<Order> displayAllOrders() {
        return orderList;
    }

    public static List<Order> displayOrdersByDate(LocalDate date) {
        try {
            List<Order> filteredDate = orderList.stream()
                    .filter(d -> d.dateInvoice.equals(date))
                    .collect(Collectors.toList());
            return filteredDate;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public static List<Order> displayOrdersByCustomer(String customerID) {
        try {
            List<Order> filteredCustomer = orderList.stream()
                    .filter(d -> d.customerID.equalsIgnoreCase(customerID))
                    .collect(Collectors.toList());
            return filteredCustomer;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public static Order displayOrderByID(String orderID) {
        try {
            Order matchedOrder = orderList.stream()
                    .filter(i -> i.orderID.equalsIgnoreCase(orderID))
                    .findFirst().get();
            return matchedOrder;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public static int totalRevenue() {
        return orderList.stream()
                .mapToInt(Order::getInvoice)
                .sum();
    }

    @Override
    public String toString() {
        return "Order" + '\n' +
                "orderID: " + orderID + '\n' +
                " Customer ID: " + customerID + '\n' +
                " Total: " + totalInvoice + " VND" + '\n' +
                " Date: " + dateInvoice + '\n' +
                " Status= " + status + '\n' +
                " Cart: " + cart + '\n' +
                "\n";
    }
}

