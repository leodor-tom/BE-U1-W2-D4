package TommasoEleodori;

import TommasoEleodori.utilities.Customer;
import TommasoEleodori.utilities.Order;
import TommasoEleodori.utilities.Product;

import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        Customer aldo = new Customer("Aldo");
        Customer giovanni = new Customer("Giovanni");
        Customer giacomo = new Customer("Giacomo");


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
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.summingDouble(Order::getPrice)));

        totalSalesPerCustomer.forEach(Application::totalAmount);

        System.out.println("******************** most expensive product ************");
        Optional<Product> mostExpensiveProduct = products.stream()
                .max(Comparator.comparingDouble(Product::getPrice));

        System.out.println("the most expensive product is: " + mostExpensiveProduct.get().getName() + " " + mostExpensiveProduct.get().getPrice() + "€");

        System.out.println("******************** average order price ************");
        double averageOrderPrice = orders.stream()
                .collect(Collectors.averagingDouble(Order::getPrice));

        System.out.println("the average price of an order is: " + averageOrderPrice + "€");
    }


    private static void accept(Customer customer, List<Order> orders1) {
        System.out.println("Customer: " + customer + ", orders: " + orders1);
    }

    private static void totalAmount(Customer customer, Double price) {
        System.out.println("Customer: " + customer + ", total amount: " + price);
    }
}
