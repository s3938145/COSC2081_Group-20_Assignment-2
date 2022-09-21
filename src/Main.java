import controller.AdminMenu;
import controller.Catalog;

import controller.UserMenu;
import model.*;
import java.util.*;
import java.io.*;

public class Main {

     public static void main(String[] args) throws IOException, ClassNotFoundException {
         //Load data from file catalog.txt
         Catalog.loadProducts();
         Order.loadOrders();
         Customer.loadCustomer();

         Scanner input = new Scanner(System.in);
         boolean isAdmin;
         System.out.println("COSC2081 GROUP ASSIGNMENT ");
         System.out.println("STORE ORDER MANAGEMENT SYSTEM");
         System.out.println("Instructor: Mr. Minh Vu");
         System.out.println("Group: 20");
         System.out.println("S3938145, Ngo Chi Binh");
         System.out.println("S3915233, Nguyen Minh Hoang");
         System.out.println("---------------------------");
         System.out.println("1. Register");
         System.out.println("2. Log in");
         int option = Integer.parseInt(input.nextLine());

         while (option != 0)
             switch (option) {
                 case 1:
                     System.out.println("Enter your username");
                     String username = input.nextLine();
                     System.out.println("Enter your desired password");
                     String password = input.nextLine();
                     System.out.println("fullName");
                     String fullName = input.nextLine();
                     System.out.println("Address");
                     String address = input.nextLine();
                     System.out.println("Phone number");
                     String phone = input.nextLine();
                     Customer.addCustomer(new Customer(username, password, fullName, address, phone));
                     System.out.println("1. Register");
                     System.out.println("2. Log in");
                     option = Integer.parseInt(input.nextLine());
                     break;
                 case 2:
                     System.out.println("Enter your username");
                     String name = input.nextLine();
                     System.out.println("Enter Password");
                     String passwd = input.nextLine();
                     Customer customer = Customer.signIn(name,passwd);
                     if (customer == null) {
                         System.out.println("Username or password is incorrect");
                         System.out.println("1. Register");
                         System.out.println("2. Log in");
                         option = Integer.parseInt(input.nextLine());
                         break;
                     } else {
                         System.out.println("Log in successful");
                         System.out.println(customer);
                         isAdmin = customer.getStatus();
                         if (!isAdmin) {
                             System.out.println("User menu");
                             UserMenu.menu();
                             option = Integer.parseInt(input.nextLine());

                             while (option != 0)
                                 switch (option){
                                     case 1:
                                         UserMenu.displayCatalog();
                                         UserMenu.menu();
                                         option = Integer.parseInt(input.nextLine());
                                         break;
                                     case 2:
                                         UserMenu.displayCatalogCategory();
                                         UserMenu.menu();
                                         option = Integer.parseInt(input.nextLine());
                                         break;
                                     case 3:
                                         UserMenu.displayCatalogPrice(Catalog.displayCatalogAll());
                                         UserMenu.menu();
                                         option = Integer.parseInt(input.nextLine());
                                         break;
                                     case 4:
                                         UserMenu.findProductByName();
                                         UserMenu.menu();
                                         option = Integer.parseInt(input.nextLine());
                                         break;
                                     case 5:
                                         UserMenu.orderItems(customer);
                                         UserMenu.menu();
                                         option = Integer.parseInt(input.nextLine());
                                         break;
                                     case 6:
                                         UserMenu.displayCustomerOrderID();
                                         UserMenu.menu();
                                         option = Integer.parseInt(input.nextLine());
                                         break;
//                                     case 7:
//                                         System.out.println("Make this customer an admin");
//                                         Customer.makeAdmin(customer);
//                                         System.out.println(customer);
//                                         Customer.saveCustomer();
//                                         UserMenu.menu();
//                                         option = Integer.parseInt(input.nextLine());
//                                         break;
                                 }

                         } else if (isAdmin) {
                             AdminMenu.menu();
                             option = Integer.parseInt(input.nextLine());

                             while (option != 0)
                                 switch (option) {
                                     case 1:
                                         AdminMenu.addNewProduct();
                                         AdminMenu.menu();
                                         option = Integer.parseInt(input.nextLine());
                                         break;
                                     case 2:
                                         UserMenu.displayCatalog();
                                         AdminMenu.menu();
                                         option = Integer.parseInt(input.nextLine());
                                         break;
                                     case 3:
                                         UserMenu.displayCatalogCategory();
                                         UserMenu.menu();
                                         option = Integer.parseInt(input.nextLine());
                                         break;
                                     case 4:
                                         AdminMenu.displayAllOrders();
                                         AdminMenu.menu();
                                         option = Integer.parseInt(input.nextLine());
                                         break;
                                     case 5:
                                         AdminMenu.displayCustomerOrder();
                                         AdminMenu.menu();
                                         option = Integer.parseInt(input.nextLine());
                                         break;
                                     case 6:
                                         AdminMenu.displayDateOrder();
                                         AdminMenu.menu();
                                         option = Integer.parseInt(input.nextLine());
                                         break;
                                     case 7:
                                         AdminMenu.displayOrderID();
                                         AdminMenu.menu();
                                         option = Integer.parseInt(input.nextLine());
                                         break;
                                     case 8:
                                         AdminMenu.storeRevenue();
                                         AdminMenu.menu();
                                         option = Integer.parseInt(input.nextLine());
                                         break;
                                 }
                         }
                     }
                     break;
         }
     }
}













