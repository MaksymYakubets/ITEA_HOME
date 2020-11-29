package ua.itea;

import java.util.concurrent.TimeUnit;

public class Pogruzchik implements Runnable {
    Telega telega;
    private int kartoha;
    private Razgruzchik razgruzchik;

    public Pogruzchik(Telega telega, int kartoha, Razgruzchik razgruzchik) {
        this.telega = telega;
        this.kartoha = kartoha;
        this.razgruzchik = razgruzchik;
    }

    public void nagruzhauTelegu() {
        System.out.println("==============================================================================");
        System.out.println("Петрович(доставка): нагружаю картоху! ");
        int mined = Math.min(kartoha, 6);
        kartoha = kartoha - mined;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        telega.putKartoha(mined);
        System.out.println("Петрович(доставка): нагрузил: " + mined + " кг картохи!");
        System.out.println("...тем временем в куче осталось " + kartoha + " кг картохи!...");
        System.out.println("Петрович(доставка): Володька, вези быстрее, твою девизию!");
    }

    @Override
    public void run() {
        synchronized (telega) {
            while (kartoha > 0) {
                while (!telega.isEmpty() || telega.getLocation().equals("razgruzchik")) {
                    try {
                        telega.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                nagruzhauTelegu();
                telega.notifyAll();
            }
            razgruzchik.setDone(true);

        }
    }
}