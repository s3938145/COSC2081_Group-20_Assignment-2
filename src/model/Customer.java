package model;


import sun.security.util.Password;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Customer extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String customerID;

    private String fullName;

    private String address;
    private String phone;

    private boolean isAdmin;

    private static List<Customer> customerList = new ArrayList<Customer>();
    private int id = customerList.size() + 1;

    public Customer(String username, String password, String fullName, String address, String phone) {
        super(username, password);
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.customerID = "C" + id;
        this.isAdmin = false;
    }

    public static void addCustomer(Customer customer) throws IOException {
        boolean exists = false;
        for (Customer c : customerList) {
            if (c.getName() == customer.getName()) {
                System.out.println("Username already exist, please try again");
                exists = true;
                break;
            } else if (c.getFullName() == customer.getFullName()) {
                System.out.println("Customer with this name already exist");
                exists = true;
                break;
            }

        }
        if (!exists) {
            customerList.add(customer);
            saveCustomer();
        }
    }

    public static List<Customer> displayCustomerAll() {
        return customerList;
    }

    public static void saveCustomer() throws IOException {
        FileOutputStream FileOut = new FileOutputStream("customers.txt");
        ObjectOutputStream ObjectOut = new ObjectOutputStream(FileOut);
        ObjectOut.writeObject(customerList);
        ObjectOut.close();
    }

    public static void loadCustomer() throws IOException, ClassNotFoundException {
        FileInputStream FileIn = new FileInputStream("customers.txt");
        ObjectInputStream ObjectIn = new ObjectInputStream(FileIn);
        customerList = (List<Customer>) ObjectIn.readObject();
        ObjectIn.close();
    }

    public static Customer signIn(String username, String password){
        try {Customer matchedCustomer = customerList.stream()
                .filter(i -> i.username.equals(username))
                .filter(i -> i.password.equals(password))
                .findFirst().get();
            return matchedCustomer;
        } catch (NoSuchElementException e) {
        return null;
        }
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public String getName() {
        return this.username;
    }

    public String getFullName() {
        return this.fullName;
    }

    public static void makeAdmin(Customer customer) {
        customer.isAdmin = true;
    }

    public boolean getStatus() {
        return this.isAdmin;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                "customerID='" + customerID + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}


