package TommasoEleodori.utilities;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Order {
    private final Long id;
    private final LocalDate orderDate;
    private final LocalDate deliveryDate;
    private final Customer customer;
    private String status;
    private List<Product> products;
    private double total;

    public Order(Customer customer, List<Product> products) {
        if (customer == null || products == null || products.isEmpty())
            throw new IllegalArgumentException("Customer and products cannot be null or empty");
        Random rndm = new Random();
        this.customer = customer;
        this.products = products;
        this.id = rndm.nextLong();
        this.orderDate = LocalDate.now();
        this.deliveryDate = orderDate.plusDays(3);
        this.status = delivery(this.deliveryDate);
        this.customer.placeOrder(this);
        this.total = products.stream().mapToDouble(Product::getPrice).sum();
    }

    private String delivery(LocalDate deliveryDate) {
        if (deliveryDate == null) throw new IllegalArgumentException("Delivery date cannot be null");
        if (LocalDate.now().equals(deliveryDate)) {
            return "The order has been delivered";
        } else return "order has been shipped";
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer getCustomer(){return customer;}

    public double getTotal() {
        return total;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", products=" + products +
                ", customer=" + customer +
                ", price=" + total +
                '}';
    }
}
