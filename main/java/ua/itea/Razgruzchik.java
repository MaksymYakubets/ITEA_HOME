package ua.itea;

import java.util.concurrent.TimeUnit;

public class Razgruzchik implements Runnable {
    Telega telega;
    Transporter transporter; //
    private volatile boolean isDone;

    public void setDone(boolean done) {
        isDone = done;

    }

    public Razgruzchik(Telega telega, Transporter transporter) {
        this.telega = telega;
        this.transporter = transporter;  //
    }

    public void razgruzhauTelegu() {
        System.out.println("Артем Беседин (АТБ): Разгружаю телегу!");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        telega.putKartoha(0);
        System.out.println("Артем Беседин (АТБ): Разгрузил телегу! Пойду покурю пока...а ты обратно тарабань");
    }

    @Override
    public void run() {
        synchronized (telega) {
            while (!isDone) {
                while (telega.isEmpty() || telega.getLocation().equals("pogruzchik")) {
                    try {
                        telega.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                razgruzhauTelegu();
                telega.notifyAll();
            }
            transporter.setDone(true);
        }
    }
}
