package ua.itea;

import java.util.concurrent.TimeUnit;

public class Transporter implements Runnable {
    Telega telega;
    private volatile boolean isDone;

    public void setDone(boolean done) {
        isDone = done;
    }

    public Transporter(Telega telega) {
        this.telega = telega;
    }

    public void vezuTelegu() {
        System.out.println("Володька(водитель кравчучки): везу, не подгоняй!");
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Володька(водитель кравчучки): по вашему указаню, мерседес прибыл!");
            if (telega.getLocation().equals("pogruzchik")) {
                telega.setLocation("razgruzchik");
            } else telega.setLocation("pogruzchik");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        synchronized (telega) {
            while (!isDone) {
                while ((telega.isEmpty() && telega.getLocation().equals("pogruzchik"))
                        || (!telega.isEmpty() && telega.getLocation().equals("razgruzchik"))
                ) {
                    try {
                        telega.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                vezuTelegu();
                telega.notifyAll();
            }
            System.out.println("==============================================================================");
            System.out.println("Артем Беседин (АТБ): УРА! Разгрузка закончена! Хлопцы, гайда по пивасику!");
            System.out.println("Артем Беседин - Динамовская школа!");
        }
    }
}
