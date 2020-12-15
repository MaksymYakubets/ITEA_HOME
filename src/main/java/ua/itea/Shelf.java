package ua.itea;

import java.util.ArrayList;
import java.util.Collection;

public class Shelf<T extends Product> {
    private Collection<T> items;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Product p : items) {
            builder.append(p.showProduct() + "\n");
        }
        return builder.toString();
    }

    public Shelf() {
        items = new ArrayList<>();
    }

    public void add(T item) {            // var 2
        items.add(item);
    }

}