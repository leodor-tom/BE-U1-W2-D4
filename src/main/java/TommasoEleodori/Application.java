package TommasoEleodori;

import TommasoEleodori.utilities.Customer;
import TommasoEleodori.utilities.Order;
import TommasoEleodori.utilities.Product;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        Customer aldo = new Customer("Aldo");
        Customer giovanni = new Customer("Giovanni");
        Customer giacomo = new Customer("Giacomo");


        List<Product> products = getProducts();


        Order orderAldo = new Order(aldo, products);
        Order orderAldo2 = new Order(aldo, products);
        Order orderAldo3 = new Order(aldo, products);
        Order orderAldo4 = new Order(aldo, products);
        Order orderAldo5 = new Order(aldo, products);
        Order orderGiovanni = new Order(giovanni, products);
        Order orderGiovanni2 = new Order(giovanni, products);
        Order orderGiovanni3 = new Order(giovanni, products);
        Order orderGiovanni4 = new Order(giovanni, products);
        Order orderGiovanni5 = new Order(giovanni, products);
        Order orderGiacomo = new Order(giacomo, products);
        Order orderGiacomo2 = new Order(giacomo, products);

        List<Order> orders = Arrays.asList(orderAldo, orderAldo2, orderAldo3, orderAldo4, orderAldo5, orderGiovanni,
                orderGiovanni2, orderGiovanni3, orderGiovanni4, orderGiovanni5, orderGiacomo, orderGiacomo2);


        System.out.println("******************** order by customer ************");
        Map< Customer, List<Order>> ordersByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));

        ordersByCustomer.forEach(Application::accept);

        System.out.println("******************** total sales per customer ************");
        Map<Customer, Double>  totalSalesPerCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.summingDouble(Order::getTotal)));

        totalSalesPerCustomer.forEach(Application::totalAmount);

        System.out.println("******************** most expensive product ************");
        Optional<Product> mostExpensiveProduct = products.stream()
                .max(Comparator.comparingDouble(Product::getPrice));

        System.out.println("the most expensive product is: " + mostExpensiveProduct.get().getName() + " " + mostExpensiveProduct.get().getPrice() + "€");

        System.out.println("******************** average order price ************");
        double averageOrderPrice = orders.stream()
                .collect(Collectors.averagingDouble(Order::getTotal));

        System.out.println("the average price of an order is: " + averageOrderPrice + "€");

        System.out.println("******************** total price per category ************");
        Map<String, Double> totalPricePerCaegory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice)));

        totalPricePerCaegory.forEach(Application::totalPerCategory);

        //****************************** file **********************************
        String filePath = "src/products.txt";
       saveProductsToDisk(products,filePath);
        List<Product> readProducts = readProductsFromDisk(filePath);
        System.out.println("new list" + readProducts);
    }

    private static List<Product> getProducts() {
        Product toy = new Product("plane", "Toy", 35);
        Product phone = new Product("pear", "Electronics", 1000);
        Product book = new Product("Big Brother", "Books", 35);
        Product book2 = new Product("The Show of Records, DELUXE", "Books", 100);
        Product book3 = new Product("Divina Commedia", "Books", 99.99);
        Product book4 = new Product("Clean code", "Books", 27);
        Product babyBottle = new Product("Baby Bottle", "Baby", 5.99);
        Product diapers = new Product("Diapers", "Baby", 29.99);
        Product stroller = new Product("Stroller", "Baby", 199.99);
        Product toyCar = new Product("toy car", "Boys", 40);
        Product actionFigure = new Product("action figure", "Boys", 15);
        Product legoSet = new Product("Lego set", "Boys", 100);

        List<Product> products = Arrays.asList(book, book2, book3, book4, toy, phone, babyBottle, diapers, stroller, toyCar, actionFigure, legoSet);
        return products;
    }


    private static void accept(Customer customer, List<Order> orders1) {
        System.out.println("Customer: " + customer + ", orders: " + orders1);
    }

    private static void totalAmount(Customer customer, Double total) {
        System.out.println("Customer: " + customer + ", total amount: " + total);
    }

    private static void  totalPerCategory(String category, Double total) {
        System.out.println("Category: " + category + ", total: " + total);
    }

    private  static void saveProductsToDisk(List<Product> products , String filePath) {
        StringBuilder sb = new StringBuilder();
        for (Product product : products) {
            sb.append(product.getName());
            sb.append("@");
            sb.append(product.getCategory());
            sb.append("@");
            sb.append(product.getPrice());
            sb.append("#");
        }

        if(!sb.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }

        try{
            FileUtils.writeStringToFile( new File(filePath), sb.toString(), "UTF-8");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static List<Product> readProductsFromDisk(String filePath) {
        List<Product> productList = new ArrayList<>();
        try {
            String data =  FileUtils.readFileToString(new File(filePath), "UTF-8");
            String[] productEntries = data.split("#");
            for (String entry : productEntries) {
                String[] fields = entry.split("@");
                if (fields.length == 3) {
                    String name = fields[0];
                    String category = fields[1];
                    double price = Double.parseDouble(fields[2]);
                    productList.add(new Product( name, category, price));
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return productList;
    }
}
