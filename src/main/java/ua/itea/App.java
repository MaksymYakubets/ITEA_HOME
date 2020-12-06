package ua.itea;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
/*
Создать коллекцию типа Class с классами Cat,BlackCat,FatCat,ThinCat,AglyCat и аннотации,которые могут быть разнесены как угодно .
Color(catcolor),Blohable, CatYears(old).нужно создать фабрику котячого фарша.если есть аннотация блохобл,то коли на фарш не подходит,
если цвет черный,то шанс не попасть в фарш 50%. Коты старше 3 лет тоже не подходят.сделать логгировние ,что произошло с котами в коллекции.*/

/* задача реализована на песиках, т.к. они вкуснее))
Не подходят на производство хот-догов такие пёсики:
-- блохастики (наличие паразитов и повреждений кожи)
-- чёрные (злые они, могут горчить) - 50% по теории вероятности
-- старше 3 лет (мясо твердое)
 */

public class App {
    public static void main(String[] args) {
        System.out.println("ЖИЛ БЫЛ ПЕС...");
        pause(1);

        System.out.println("МНОГО ПСОВ...");
        pause(1);

        new PrintDog();
        pause(1);

        System.out.println("СЛИШКОМ МНОГО ПСОВ... и потому злые ЧЕЛОВЕКИ решили уменьшить их популяцию");
        pause(1);

        System.out.println("И так появилась фабрика хот-догов и начался безпощадный кастинг...");
        pause(2);

        Collection<Class<? extends SomeDogInterface>> coll = Arrays.asList(
                BlackDog.class,
                FatDog.class,
                ThinDog.class,
                UglyDog.class,
                Dog.class,
                OldDog.class,
                SimpleDog.class
                );

        HotDogFactory hotDogFactory = new HotDogFactory();
        int totalHotDogs = 0;
        int totalSoaps = 0;

        for (Class<? extends SomeDogInterface> dogClass : coll) {
            HotDog hotDog = hotDogFactory.createHotDog(dogClass);

            if (hotDog == null) {
                totalSoaps++;
            } else {
                totalHotDogs++;
            }
            pause(1);
        }
        pause(2);
        System.out.println("ИТОГО:");
        System.out.println("====================================");
        System.out.println("ХОТДОГОВ: " + totalHotDogs);

        new PrintHotDog();
        pause(1);

        System.out.println("====================================");
        System.out.println("На мыло: " + totalSoaps);
        new PrintSoap();

        pause(1);
        System.out.println("ГАВ!");
    }

    private static void pause(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
