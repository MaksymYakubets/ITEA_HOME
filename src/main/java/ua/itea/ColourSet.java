package ua.itea;

import java.util.Random;

public enum ColourSet {
    WHITE,
    RED,
    BLACK,
    GREEN,
    YELLOW,
    PINK,
    BROWN,
    MAGENTA,
    ORANGE,
    CYAN,
    BLUE,
    GRAY,
    PURPLE,
    SILVER,
    GOLD;

    private static final ColourSet[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static ColourSet getRandomColour() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
