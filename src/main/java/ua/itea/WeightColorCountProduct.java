package ua.itea;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class WeightColorCountProduct extends WeightColorProduct {
    private int count;

    @Override
    public String toString() {
        return  super.toString() + "\t" + " || count=" + "\t" + count +
                ';';
    }

    public static WeightColorCountProduct newInstance() {
        WeightColorCountProduct product = new WeightColorCountProduct("Name: '" + Assortment.getRandomItem() + "'");
        product.setPrice((BigDecimal) new BigDecimal(2714.07 + Math.random() * 7520.61).setScale(2, RoundingMode.HALF_DOWN));
        product.setWeight((BigDecimal) new BigDecimal(0.1 + Math.random() * 8.32).setScale(1, RoundingMode.HALF_UP));
        product.setColourSet(ColourSet.getRandomColour());
        product.setCount((int) (1 + Math.random() * 70));
        return product;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public WeightColorCountProduct(String name) {
        super(name);
    }

}
