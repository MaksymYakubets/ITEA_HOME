package ua.itea;

import java.util.concurrent.TimeUnit;

/*
Задача:
Есть 3 рабочих,
У каждого своя специал-я: грузчик, транспортёр, разгрузчик
В куче 100 чего-то
Тележка вмещает 6 кг песка
Грузчик набирает в тележку 3кг/с
Когда тележка полн, транспортёр везёт 5 сек
Разгрузчик разгружает 2 кг/с
И транспортёр везёт (тоже 5 сек) телегу обратно грузчику.

Нужны подробные логи, кто в данный момент работает, и кто кому что делегирует

Потоки должны быть приостановлены, только 1 в единицу времени.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Один день из жизни Артема Беседина!");
        System.out.println("==============================================================================");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("В АТБ привезли машину картошки....");
        //     System.out.println("==============================================================================");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Telega telega = new Telega("pogruzchik");
        Transporter transporter = new Transporter(telega);
        Razgruzchik razgruzchik = new Razgruzchik(telega, transporter);
        Pogruzchik pogruzchik = new Pogruzchik(telega, 100, razgruzchik);


        new Thread(transporter).start();
        new Thread(pogruzchik).start();
        new Thread(razgruzchik).start();
    }
}