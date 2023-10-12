package TommasoEleodori.utilities;

import java.util.Random;

public class Product {
    private final Long id;
    private final String name;
    private final String category;
    private double price;


    public Product(String name, String category, double price) {
        if (name == null || name.trim().isEmpty() || category == null || category.trim().isEmpty() || price < 0)
            throw new IllegalArgumentException("Invalid product data");
        Random rndm = new Random();
        this.name = name;
        this.category = category;
        this.price = price;
        this.id = rndm.nextLong();
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
