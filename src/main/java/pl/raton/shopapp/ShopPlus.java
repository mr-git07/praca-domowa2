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
@Profile("Plus")
@ConfigurationProperties(prefix = "profil-plus")
public class ShopPlus {

    private Product product;
    private Double vat;
    private List<Product> cart;

    @Autowired
    public ShopPlus(Product product) {
        this.product = product;
        cart = new ArrayList<>();
        cart.add(new Product("mleko"));
        cart.add(new Product("piwo"));
        cart.add(new Product("woda"));
        cart.add(new Product("masło"));
        cart.add(new Product("sok"));
    }

    public ShopPlus(Double vat) {
        this.vat = vat;
    }

    public void showProducts() {
        cart.forEach(System.out::println);
    }

    public void cartValue() {

        final Double totalPrice =  cart
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();

        double priceWithTax = totalPrice * vat;

        System.out.println("Wartosć wszystkich produktów w koszyku: " + totalPrice + "zł");
        System.out.println("Wartosć wszystkich produktów w koszyku + VAT: " + priceWithTax + "zł");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        System.out.println("Shop Plus ");
        showProducts();
        cartValue();
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }
}