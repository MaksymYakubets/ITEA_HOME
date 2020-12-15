package ua.itea;

import java.util.Random;

public enum Assortment {
    Meat,
    Bread,
    Milk,
    Potato,
    HotDog,
    BeefStake,
    CocaCola,
    Tea,
    Coffee,
    Salo,
    Beer,
    Parmezan,
    Condoms,
    Whisky,
    Tequila,
    Cigares,
    Drugs,
    Weapon,
    Jeans,
    Girls,
    Cocaine;

    private static final Assortment[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static Assortment getRandomItem() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
