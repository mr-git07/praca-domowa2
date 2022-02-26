package pl.raton.shopapp;

import org.springframework.stereotype.Component;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class Product {

    private String name;
    private double price = randomPrice();

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    private double randomPrice() {
        double min = 50;
        double max = 300;
        price = ThreadLocalRandom.current().nextDouble(min, max);
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}