package ua.itea;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WeightColorProduct extends WeightProduct {
    private ColourSet colourSet;

    @Override
    public String toString() {
        return super.toString() + "\t" + " || colour = " + "\t" + colourSet;
    }

    public static WeightColorProduct newInstance() {
        WeightColorProduct product = new WeightColorProduct("Name: '" + Assortment.getRandomItem() + "'");
        product.setPrice((BigDecimal) new BigDecimal(2714.07 + Math.random() * 7520.61).setScale(2, RoundingMode.HALF_DOWN));
        product.setWeight((BigDecimal) new BigDecimal(0.1 + Math.random() * 8.32).setScale(1, RoundingMode.HALF_UP));
        product.setColourSet(ColourSet.getRandomColour());
        return product;
    }

    public void setColourSet(ColourSet colourSet) {
        this.colourSet = colourSet;
    }

    public WeightColorProduct(String name) {
        super(name);
    }

}