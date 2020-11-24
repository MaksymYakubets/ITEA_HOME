package itea;

import java.util.Random;

/**
 * домашка - по концепции сборки предыдущего долмашнего заданя по сборки джарника,собрать jar файл,который записывает в базу ключи,
 * который указывают при запуске и выводит все содержимое таблицы (куда ключи пишутся). конфиг базы (хост,пользователь,пароль) вынести в отдельный файл ,
 * который расположен рядолм с джарником
 */
public class App {
    public static void main(String[] args) {
        // генератор рандомного юзера (из существующих в БД)
        int a = (int) (1+ Math.random() * 9 );
        System.out.println("user_id:" + a);

       // генератор рандомного логина
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder(10);
        Random randomLogin = new Random();
        for (int i = 0; i < 10; i++) {
            char c = chars[randomLogin.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println("New login " + output);

        // генератор рандомного пароля
        char[] chars2 = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ)(*?:%".toCharArray();
        StringBuilder sb2 = new StringBuilder(20);
        Random randomPass = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars2[randomPass.nextInt(chars2.length)];
            sb2.append(c);
        }
        String output2 = sb2.toString();
        System.out.println("New password " + output2);

        // инсерт нового логина и пароля
        new InsertPass().InsertPassword(a, sb.toString(), sb2.toString());

        // выборка из базы
        new SelectPass().selectPassword();

    }

}