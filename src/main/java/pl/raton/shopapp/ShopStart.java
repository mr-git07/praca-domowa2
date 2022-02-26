package pl.raton.shopapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("Start")
@ConfigurationProperties(prefix = "profil-start")
public class ShopStart {

    private Product product;
    private List<Product> cart;

    @Autowired
    public ShopStart(Product product) {
        this.product = product;
        cart = new ArrayList<>();
        cart.add(new Product("mleko"));
        cart.add(new Product("piwo"));
        cart.add(new Product("woda"));
        cart.add(new Product("masło"));
        cart.add(new Product("sok"));
    }

    public ShopStart() {
    }

    public void showProducts() {
        cart.forEach(System.out::println);
    }

    public void cartValue() {

        final Double totalPrice =  cart
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println("Wartosć wszystkich produktów w koszyku: " + totalPrice + "zł");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        System.out.println("Profil Shop Start ");
        showProducts();
        cartValue();
    }
}