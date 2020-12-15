package ua.itea;
/*Есть три класса(базовые поля имя и цена)-Product,WeightProduct, WeightColorProduct.
Реализовать полку-дженерик,на которой могут сохранятся любой из товаров с методами showProduct,
которые могли бы в зависимости от обьекта,выводить значения всех полей товара.*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class App {
    public static void main(String[] args) {
// 1-й варіант
        var topShelf = List.of(Product.newInstance(), WeightProduct.newInstance(), WeightColorProduct.newInstance(),
                WeightColorCountProduct.newInstance());
        System.out.println("Верхня полиця:");
        System.out.println("===============================================================================");
        System.out.println(topShelf);
        System.out.println("===============================================================================");
// 2-й варіант
        Collection<Product> middleShelf = Arrays.asList(Product.newInstance(), WeightProduct.newInstance(), WeightColorProduct.newInstance(),
                WeightColorCountProduct.newInstance());
        System.out.println("Середня полиця:");
        System.out.println("===============================================================================");
        System.out.println(middleShelf);
        System.out.println("===============================================================================");
// 3-й варіант
        Shelf<Product> downShelf = new Shelf<>();
        downShelf.add(Product.newInstance());
        downShelf.add(WeightProduct.newInstance());
        downShelf.add(WeightColorProduct.newInstance());
        downShelf.add(WeightColorCountProduct.newInstance());
        System.out.println("Нижня полиця:");
        System.out.println("===============================================================================");
        System.out.println(downShelf);
        System.out.println("===============================================================================");
    }
}