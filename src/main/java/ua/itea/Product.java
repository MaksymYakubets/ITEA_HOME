package ua.itea;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private String name;
    private BigDecimal price;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String showProduct() {
        return toString();
    }

    @Override
    public String toString() {
        return "\t" + getName() + "\t"  + "|| price = " + "\t" + getPrice();
    }

    public static Product newInstance() {
        Product product = new Product("Name: '" + Assortment.getRandomItem() + "'");
        product.setPrice((BigDecimal) new BigDecimal(1000.98 + Math.random() * 4000.46).setScale(2, RoundingMode.HALF_DOWN));
        return product;
    }
}