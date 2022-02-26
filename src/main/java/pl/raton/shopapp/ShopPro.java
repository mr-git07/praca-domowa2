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
@Profile("Pro")
@ConfigurationProperties(prefix = "profil-pro")
public class ShopPro {

    private Product product;
    private Double vat;
    private Double discount;
    private List<Product> cart;

    @Autowired
    public ShopPro(Product product) {
        this.product = product;
        cart = new ArrayList<>();
        cart.add(new Product("mleko"));
        cart.add(new Product("piwo"));
        cart.add(new Product("woda"));
        cart.add(new Product("masło"));
        cart.add(new Product("sok"));
    }

    public ShopPro(Double vat, Double discount) {
        this.vat = vat;
        this.discount = discount;
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
        double priceWithDiscount = priceWithTax - discount;

        System.out.println("Wartosć wszystkich produktów w koszyku: " + totalPrice + "zł");
        System.out.println("Wartosć wszystkich produktów w koszyku + VAT: " + priceWithTax + "zł");
        System.out.println("Wartosć wszystkich produktów z VAT. Dodano RABAT!: " + priceWithDiscount + "zł");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        System.out.println("Profil PRO! ");
        showProducts();
        cartValue();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }
}