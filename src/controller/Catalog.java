package controller;

import model.Product;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Catalog extends Product{

    private static List<Catalog> catalog = new ArrayList<>();

    private int id = catalog.size() + 1;

    public Catalog(String name, String cat, int price) {
        super(name, cat, price);
        super.productID = "P" + cat.charAt(0) + id;
    }

    public static void loadProducts() {
        Scanner input;
        try {
            input = new Scanner(new File("catalog.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        input.useDelimiter("-|\n");

        while (input.hasNext()) {
            String id = input.next();
            //Read product's name
            String name = input.next();
            //Read product's category
            String cat = input.next();
            //Read product's price
            int price = Integer.parseInt(input.next().trim());

            //Create object
            catalog.add(new Catalog(name,cat,price));
        }
    }

    public static void addProduct(Catalog product) throws IOException {
        catalog.add(product);
        //Write to file catalog.txt
        PrintWriter pw = new PrintWriter(new FileWriter("catalog.txt",true));
        pw.println(product.getProductID() + "-" + product.getName()+"-"+product.getCat()+"-"+product.getPrice());
        pw.close();
    }

    public static List<Catalog> displayCatalogAll() {

        return catalog;
    }

    public static List<Product> displayCatalogFiltered(String filter) {
        List<Product> filteredCat = catalog.stream()
                 .filter(s -> s.getCat().equalsIgnoreCase(filter))
                 .collect(Collectors.toList());
        return filteredCat;
    }

    public static Product searchProduct(String productName) {
        try {
            Product matchedProduct = catalog.stream()
                    .filter(p -> p.getName().equalsIgnoreCase(productName))
                    .findFirst().get();
            return matchedProduct;
        } catch (NoSuchElementException e) {
            return null;
        }

    }

    public static List<Catalog> sortCatelogPrice(List<Catalog> list){
        Collections.sort(list, Product.sortByPrice);
        return list;
    }

}






