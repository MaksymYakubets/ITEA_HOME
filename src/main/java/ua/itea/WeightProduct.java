package ua.itea;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WeightProduct extends Product {
    private BigDecimal weight;

    @Override
    public String toString() {
        return "\n" + super.toString() + "\t" + " || weight=" + "\t" + weight ;
    }

    public static WeightProduct newInstance() {
        WeightProduct prod = new WeightProduct("Name: '" + Assortment.getRandomItem() + "'");
        prod.setPrice((BigDecimal) new BigDecimal(741.33 + Math.random() * 1542.99).setScale(2, RoundingMode.HALF_DOWN));
        prod.setWeight((BigDecimal) new BigDecimal(0.5 + Math.random() * 11.99).setScale(1, RoundingMode.HALF_UP));
        return prod;
    }

    public WeightProduct(String name) {
        super(name);
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

}